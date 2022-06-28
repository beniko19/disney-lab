package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.genre.GenreDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieBasicDTO;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import com.alkemy.disney.alkemylab.mapper.GenreMapper;
import com.alkemy.disney.alkemylab.repository.GenreRepository;
import com.alkemy.disney.alkemylab.repository.MovieRepository;
import com.alkemy.disney.alkemylab.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;

    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        verifyAndAddMovies(dto, entity);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntity2DTOList(entities);
        return result;
    }

    private void verifyAndAddMovies(GenreDTO dto, GenreEntity entity) {
        List<MovieEntity> movies2Add2Genre = new ArrayList<>();
        if (dto.getMovies() != null) {
            List<MovieBasicDTO> moviesFromDTO = dto.getMovies();
            moviesFromDTO.stream().forEach(m -> {
                Optional<MovieEntity> movie = movieRepository.findById(m.getId());
                if (movie.isPresent())
                    movies2Add2Genre.add(movie.get());
            });
        }
        entity.setMovies(movies2Add2Genre);
    }
}
