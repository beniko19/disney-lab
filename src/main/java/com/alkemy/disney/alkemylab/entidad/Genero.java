package com.alkemy.disney.alkemylab.entidad;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "genero")
@Data
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String imagen;
    private String peliculas;
}
