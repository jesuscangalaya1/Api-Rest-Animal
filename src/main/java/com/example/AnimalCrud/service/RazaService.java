package com.example.AnimalCrud.service;


import com.example.AnimalCrud.dao.RazaRepository;
import com.example.AnimalCrud.entity.Animal;
import com.example.AnimalCrud.entity.Raza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RazaService {

    @Autowired
    private RazaRepository razaRepository;

    public List<Raza> list() {
        return razaRepository.findAll();
    }

    public Optional<Raza> getOne(Long id) {
        return razaRepository.findById(id);
    }

    public Optional<Raza> getByNombre(String nombre) {
        return razaRepository.findByNombre(nombre);
    }

    public void save(Raza raza) {
        razaRepository.save(raza);
    }

    public void delete(Long id) {
        razaRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return razaRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return razaRepository.existsByNombre(nombre);
    }


}
