package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.GenreDTO;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.mapper.GenreMapper;
import com.alkemy.disney.alkemylab.repository.GenreRepository;
import com.alkemy.disney.alkemylab.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;
    @Override
    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO> result = genreMapper.genreEntity2DTOList(entities);
        return result;
    }
}
