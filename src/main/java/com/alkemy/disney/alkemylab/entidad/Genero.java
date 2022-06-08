package com.alkemy.disney.alkemylab.entidad;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "genero")
@Data
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String imagen;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "genero_pelicula",
            joinColumns = @JoinColumn(name = "genero_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id")
    )
    private Set<Pelicula> peliculas;

}
