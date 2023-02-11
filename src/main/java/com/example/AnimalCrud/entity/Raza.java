package com.example.AnimalCrud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Raza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String color;
    private float precio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raza")
    @JsonIgnore //fasterXml
    private Set<Animal> animales;


}
