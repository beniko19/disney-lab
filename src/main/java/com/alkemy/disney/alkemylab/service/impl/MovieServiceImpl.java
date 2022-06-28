package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.character.CharacterBasicDTO;
import com.alkemy.disney.alkemylab.dto.genre.GenreBasicDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieFiltersDTO;
import com.alkemy.disney.alkemylab.entity.*;
import com.alkemy.disney.alkemylab.exception.ParamNotFound;
import com.alkemy.disney.alkemylab.mapper.CharacterMapper;

import com.alkemy.disney.alkemylab.mapper.GenreMapper;
import com.alkemy.disney.alkemylab.mapper.MovieMapper;
import com.alkemy.disney.alkemylab.repository.CharacterRepository;
import com.alkemy.disney.alkemylab.repository.GenreRepository;
import com.alkemy.disney.alkemylab.repository.MovieRepository;
import com.alkemy.disney.alkemylab.repository.specification.MovieSpecification;
import com.alkemy.disney.alkemylab.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
    private GenreRepository genreRepository;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    CharacterMapper characterMapper;

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.INSTANCE.movieDTO2Entity(dto);
        verifyAndAddCharacters2Movie(dto, entity);
        verifyAndAddGenres2Movie(dto, entity);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved);

        return result;
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntity2DTOList(entities);
        return result;
    }

    public List<MovieDTO> getByFilters(String tittle, String order, List<Long> genreId) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(tittle, order, genreId);
        //TODO
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntity2DTOList(entities);
        return dtos;
    }

    @Transactional
    public void delete(Long id) {
        getMovieEntity(id);
        movieRepository.deleteByIdMovie(id);
    }

    public MovieDTO update(Long id, MovieDTO movie) {
        Optional<MovieEntity> entity = getMovieEntity(id);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid movie ID");
        MovieEntity updatedMovie = movieMapper.movieDTO2Entity(movie);
        entity.get().setTittle(updatedMovie.getTittle());
        entity.get().setImage(updatedMovie.getImage());
        entity.get().setCreationDate(updatedMovie.getCreationDate());
        entity.get().setRating(updatedMovie.getRating());
        movieRepository.save(entity.get());
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get());
        return result;
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        Optional<MovieEntity> entity = getMovieEntity(id);
        MovieDTO result = movieMapper.movieEntity2DTO(entity.get());
        return result;
    }

    public MovieDTO addCharactersToMovie(Long id, List<Long> charactersId) {
        Optional<MovieEntity> movie = getMovieEntity(id);
        charactersId.stream().forEach(characterId -> {
            Optional<CharacterEntity> charOptional = characterRepository.findById(characterId);
            if (charOptional.isPresent())
                movie.get().getCharacters().add(charOptional.get());
        } );
        movieRepository.save(movie.get());
        return movieMapper.movieEntity2DTO(movie.get());
    }

    public MovieDTO removeCharactersFromMovie(Long id, List<Long> charactersId) {
        Optional<MovieEntity> movie = getMovieEntity(id);
        charactersId.stream().forEach(characterId -> {
            Optional<CharacterEntity> charOptional = characterRepository.findById(characterId);
            if (charOptional.isPresent())
                movie.get().getCharacters().remove(charOptional.get());
        } );
        movieRepository.save(movie.get());
        return movieMapper.movieEntity2DTO(movieRepository.getReferenceById(id));
    }

    private Optional<MovieEntity> getMovieEntity(Long id) {
        Optional<MovieEntity>  movie = movieRepository.findById(id);
        if (!movie.isPresent())
            throw new ParamNotFound("Invalid movie id");
        return movie;
    }

    private void verifyAndAddCharacters2Movie(MovieDTO dto, MovieEntity entity) {
        List<CharacterEntity> characters2Add2Movie = new ArrayList<>();
        if (dto.getCharacters() != null ){
            List<CharacterBasicDTO> charactersFromDTO = dto.getCharacters();
            charactersFromDTO.stream().forEach(c -> {
                Optional<CharacterEntity> character = characterRepository.findById(c.getId());
                if (character.isPresent())
                    characters2Add2Movie.add(character.get());
            });
        }
        entity.setCharacters(characters2Add2Movie);
    }

    private void verifyAndAddGenres2Movie(MovieDTO dto, MovieEntity entity) {
        List<GenreEntity> genres2Add2Movie = new ArrayList<>();
        if (dto.getGenres() != null) {
            List<GenreBasicDTO> genresFromDTO = dto.getGenres();
            genresFromDTO.stream().forEach(g -> {
                Optional<GenreEntity> genre = genreRepository.findById(g.getId());
                if (genre.isPresent())
                    genres2Add2Movie.add(genre.get());
            });
        }
        entity.setGenres(genres2Add2Movie);
    }

}
