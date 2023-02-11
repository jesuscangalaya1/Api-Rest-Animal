package com.example.AnimalCrud.dao;

import com.example.AnimalCrud.entity.Animal;
import com.example.AnimalCrud.entity.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Long> {

    Optional<Raza> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
