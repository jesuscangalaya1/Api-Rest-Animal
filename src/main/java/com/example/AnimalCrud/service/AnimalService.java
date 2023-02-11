package com.example.AnimalCrud.service;

import com.example.AnimalCrud.dao.AnimalRepository;
import com.example.AnimalCrud.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> list(){
        return animalRepository.findAll();
    }

    public Optional<Animal> getOne(Long id){
        return animalRepository.findById(id);
    }

    public List<Animal> getAllByNombre(String nombre){
        return animalRepository.findAllByNombre(nombre);
    }

    public void save(Animal animal){
        animalRepository.save(animal);
    }
    public void delete(Long id){
        animalRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return animalRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return animalRepository.existsAllByNombre(nombre);
    }


}
