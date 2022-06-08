package com.alkemy.disney.alkemylab.entidad;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "pelicula")
public class Pelicula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int calificacion;
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "pelicula_personaje",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private Set<Personaje> personajes;
    private String imagen;
    @ManyToMany(mappedBy = "peliculas", cascade = CascadeType.ALL)
    private List<Genero> generos = new ArrayList<>();

}
