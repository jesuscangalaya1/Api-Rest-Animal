package com.example.AnimalCrud.dto;

import com.example.AnimalCrud.entity.Raza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDto {

    @NotBlank
    private String nombre;

    private int edad;

    private float precio;

    private Raza raza;

}
