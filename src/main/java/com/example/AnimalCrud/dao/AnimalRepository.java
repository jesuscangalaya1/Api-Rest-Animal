package com.example.AnimalCrud.dao;

import com.example.AnimalCrud.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findAllByNombre(String nombre);
    boolean existsAllByNombre(String nombre);
}
