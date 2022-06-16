package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.dto.MovieFiltersDTO;
import com.alkemy.disney.alkemylab.entity.GenreMovieEntity;
import com.alkemy.disney.alkemylab.entity.MovieCharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import com.alkemy.disney.alkemylab.mapper.MovieMapper;
import com.alkemy.disney.alkemylab.repository.GenreMovieRepository;
import com.alkemy.disney.alkemylab.repository.MovieCharacterRepository;
import com.alkemy.disney.alkemylab.repository.MovieRepository;
import com.alkemy.disney.alkemylab.repository.specification.MovieSpecification;
import com.alkemy.disney.alkemylab.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private GenreMovieRepository genreMovieRepository;
    @Autowired
    private MovieSpecification movieSpecification;

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved);

        //addGenresAndCharacters(dto, result);

        return result;
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntity2DTOList(entities);
        return result;
    }

    public List<MovieDTO> getByFilters(String tittle, String order, Integer rating/*, List<CharacterDTO> characters, List<GenreDTO> genres*/) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(tittle, rating, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntity2DTOList(entities);
        return dtos;
    }

    private void addGenresAndCharacters(MovieDTO dto, MovieDTO result) {
        dto.getCharacters().forEach(character -> {
            MovieCharacterEntity movieCharacter = new MovieCharacterEntity();
            movieCharacter.setMovieId(result.getId());
            movieCharacter.setCharacterId(character.getId());
            movieCharacterRepository.save(movieCharacter);
        });
        dto.getGenres().forEach(genre -> {
            GenreMovieEntity genreMovie = new GenreMovieEntity();
            genreMovie.setMovieId(result.getId());
            genreMovie.setGenreId(genre.getId());
            genreMovieRepository.save(genreMovie);
        });
    }
}
