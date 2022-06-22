package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.dto.MovieFiltersDTO;
import com.alkemy.disney.alkemylab.entity.*;
import com.alkemy.disney.alkemylab.mapper.CharacterMapper;
import com.alkemy.disney.alkemylab.mapper.GenreMapper;
import com.alkemy.disney.alkemylab.mapper.MovieMapper;
import com.alkemy.disney.alkemylab.repository.GenreMovieRepository;
import com.alkemy.disney.alkemylab.repository.MovieCharacterRepository;
import com.alkemy.disney.alkemylab.repository.MovieRepository;
import com.alkemy.disney.alkemylab.repository.specification.MovieSpecification;
import com.alkemy.disney.alkemylab.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private GenreMovieRepository genreMovieRepository;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    CharacterMapper characterMapper;

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved);
        addGenresAndCharacters(dto, result);
        return result;
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntity2DTOList(entities);
        result.forEach(this::loadCharactersAndGenres);
        return result;
    }

    private void loadCharactersAndGenres(MovieDTO movie) {
        List<GenreEntity> genresMovieEntity = genreMovieRepository.loadGenres2Movie(movie.getId());
        List<CharacterEntity> charactersMovieEntity = movieCharacterRepository.loadCharacters2Movie(movie.getId());
        movie.setCharacters(characterMapper.characterEntity2DTOList(charactersMovieEntity));
        movie.setGenres(genreMapper.genreEntity2DTOList(genresMovieEntity));
    }

    public List<MovieDTO> getByFilters(String tittle, String order, Integer rating, String characterName, String genreName) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(tittle, rating, order, characterName, genreName);
        //TODO
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntity2DTOList(entities);
        dtos.forEach(this::loadCharactersAndGenres);
        return dtos;
    }

    @Transactional
    public void delete(Long id) {
        movieCharacterRepository.deleteMovie(id);
        movieRepository.delete(movieRepository.getReferenceById(id));
    }

    private void addGenresAndCharacters(MovieDTO dto, MovieDTO result) {
        result.setCharacters(dto.getCharacters());
        dto.getCharacters().forEach(character -> {
            MovieCharacterEntity movieCharacter = new MovieCharacterEntity();
            movieCharacter.setMovieId(result.getId());
            movieCharacter.setCharacterId(character.getId());
            movieCharacterRepository.save(movieCharacter);
        });
        result.setGenres(dto.getGenres());
        dto.getGenres().forEach(genre -> {
            GenreMovieEntity genreMovie = new GenreMovieEntity();
            genreMovie.setMovieId(result.getId());
            genreMovie.setGenreId(genre.getId());
            genreMovieRepository.save(genreMovie);
        });
    }
}
