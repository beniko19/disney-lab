package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.MovieDTO;
import com.alkemy.disney.alkemylab.dto.MovieFiltersDTO;
import com.alkemy.disney.alkemylab.entity.*;
import com.alkemy.disney.alkemylab.exception.ParamNotFound;
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
import java.util.Optional;

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
        if (dto.getCharacters() != null)
            addCharactersToMovie(dto, result);
        if (dto.getGenres() != null)
            addGenresToMovie(dto, result);
        return result;
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntity2DTOList(entities);
        result.forEach(this::loadGenresToMovie);
        result.forEach(this::loadCharactersToMovie);
        return result;
    }

    public List<MovieDTO> getByFilters(String tittle, String order, Integer rating, Long characterId, Long genreId) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(tittle, rating, order, characterId, genreId);
        //TODO
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntity2DTOList(entities);
        dtos.forEach(this::loadGenresToMovie);
        return dtos;
    }

    @Transactional
    public void delete(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid movie id");
        movieRepository.deleteByIdMovie(id);
        Optional<MovieCharacterEntity> movieCharacter = movieCharacterRepository.findByMovieId(id);
        if (movieCharacter.isPresent())
            movieCharacterRepository.deleteMovie(id);
    }
    @Transactional
    public MovieDTO addCharactersToMovie(Long movieId, List<Long> charactersId) {
        Optional<MovieEntity> entity = movieRepository.findById(movieId);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid movie id");
        charactersId.forEach(id -> {
            MovieCharacterEntity movieCharacter = new MovieCharacterEntity();
            movieCharacter.setMovieId(movieId);
            movieCharacter.setCharacterId(id);
            movieCharacterRepository.save(movieCharacter);
        });
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get());
        loadCharactersToMovie(result);
        return result;
    }
    @Transactional
    public MovieDTO removeCharactersFromMovie(Long movieId, List<Long> charactersId) {
        Optional<MovieEntity> entity = movieRepository.findById(movieId);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid movie id");
        charactersId.stream().forEach(id -> movieCharacterRepository.deleterCharacterFromMovie(id, movieId));
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get());
        loadCharactersToMovie(result);
        return result;
    }

    public MovieDTO update(Long id, MovieDTO movie) {
        Optional<MovieEntity>  entity = movieRepository.findById(id);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid movie id");
        MovieEntity updatedMovie = movieMapper.movieDTO2Entity(movie);
        entity.get().setImage(updatedMovie.getImage());
        entity.get().setCreationDate(updatedMovie.getCreationDate());
        entity.get().setRating(updatedMovie.getRating());
        movieRepository.save(entity.get());
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get());
        loadGenresToMovie(result); loadCharactersToMovie(result);
        return result;
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        Optional<MovieEntity>  entity = movieRepository.findById(id);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid movie id");
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get());
        loadCharactersToMovie(result); loadGenresToMovie(result);
        return result;
    }

    private void addCharactersToMovie(MovieDTO dto, MovieDTO result) {
        result.setCharacters(dto.getCharacters());
        dto.getCharacters().forEach(character -> {
            MovieCharacterEntity movieCharacter = new MovieCharacterEntity();
            movieCharacter.setMovieId(result.getId());
            movieCharacter.setCharacterId(character.getId());
            movieCharacterRepository.save(movieCharacter);
        });
        loadCharactersToMovie(result);
    }

    private void addGenresToMovie(MovieDTO dto, MovieDTO result) {
        dto.getGenres().forEach(genre -> {
            GenreMovieEntity genreMovie = new GenreMovieEntity();
            genreMovie.setMovieId(result.getId());
            genreMovie.setGenreId(genre.getId());
            genreMovieRepository.save(genreMovie);
        });
        loadGenresToMovie(result);
    }

    private void loadGenresToMovie(MovieDTO movie) {
        List<GenreEntity> genresMovieEntity = genreMovieRepository.loadGenres2Movie(movie.getId());
        movie.setGenres(genreMapper.genreEntity2DTOList(genresMovieEntity));
    }

    private void loadCharactersToMovie(MovieDTO movie) {
        List<CharacterEntity> charactersMovieEntity = movieCharacterRepository.loadCharacters2Movie(movie.getId());
        movie.setCharacters(characterMapper.characterEntity2DTOList(charactersMovieEntity));
    }
}
