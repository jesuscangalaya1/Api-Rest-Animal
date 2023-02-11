package com.example.AnimalCrud.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nombre")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int edad;
    private float precio;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "raza_id", nullable = false)
    private Raza raza;

    public Animal(String nombre, int edad, float precio) {
        this.nombre = nombre;
        this.edad = edad;
        this.precio = precio;
    }
}
