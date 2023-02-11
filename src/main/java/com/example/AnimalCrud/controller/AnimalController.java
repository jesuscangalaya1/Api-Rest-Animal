package com.example.AnimalCrud.controller;

import com.example.AnimalCrud.dto.AnimalDto;
import com.example.AnimalCrud.dto.Mensaje;
import com.example.AnimalCrud.entity.Animal;
import com.example.AnimalCrud.entity.Raza;
import com.example.AnimalCrud.service.AnimalService;
import com.example.AnimalCrud.service.RazaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private RazaService razaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Animal>> List() {
        List<Animal> list = animalService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detalles/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        if (!animalService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El animal no existe"), HttpStatus.NOT_FOUND);
        if (animalService.getOne(id).isPresent()) {
            Animal animal = animalService.getOne(id).get();
            return new ResponseEntity<>(animal, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensaje("En animal no existe "), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/detallesnombre/{nombre}")
    public ResponseEntity<?> getByNombre(@PathVariable String nombre) {
        if (!animalService.existsByNombre(nombre))
            return new ResponseEntity<>(new Mensaje("El animal no existe"), HttpStatus.NOT_FOUND);
        List<Animal> animales = animalService.getAllByNombre(nombre);
        return new ResponseEntity<>(animales, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody AnimalDto animalDto) {
        if (StringUtils.isBlank(animalDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El animal no existe"), HttpStatus.NOT_FOUND);
        Raza raza;
        Animal animal = new Animal(animalDto.getNombre(), animalDto.getEdad(), animalDto.getPrecio());

        if (razaService.getByNombre(animalDto.getRaza().getNombre()).isPresent()) {
            raza = razaService.getByNombre(animalDto.getRaza().getNombre()).get();
            animal.setRaza(raza);
        } else {
            if (StringUtils.isBlank(animalDto.getRaza().getNombre()))
                return new ResponseEntity<>(new Mensaje("El nombre de la raza es obligatorio"), HttpStatus.NOT_FOUND);
            Raza nuevaRaza = animalDto.getRaza();
            animal.setRaza(nuevaRaza);
            if (nuevaRaza.getAnimales() != null) {
                nuevaRaza.getAnimales().add(animal);
            } else {
                Set<Animal> animales = new HashSet<>();
                animales.add(animal);
                nuevaRaza.setAnimales(animales);
            }
        }
        animalService.save(animal);
        return new ResponseEntity<>(new Mensaje("El animal ha sido creado"), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnimalDto animalDto) {
        if (!animalService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El animal no existe"), HttpStatus.NOT_FOUND);
        if (StringUtils.isBlank(animalDto.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre del animal es obligatorio"), HttpStatus.BAD_REQUEST);

        Animal animal = animalService.getOne(id).get();
        animal.setNombre(animalDto.getNombre());
        animal.setEdad(animalDto.getEdad());
        animal.setPrecio(animalDto.getPrecio());
        animal.setRaza(animalDto.getRaza());

        animalService.save(animal);
        return new ResponseEntity<>(new Mensaje("El animal ha sido actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!animalService.existsById(id))
            return new ResponseEntity<>(new Mensaje("El animal no existe"), HttpStatus.NOT_FOUND);
        animalService.delete(id);
        return new ResponseEntity<>(new Mensaje("El animal ha sido eliminado"), HttpStatus.OK);
    }
}







