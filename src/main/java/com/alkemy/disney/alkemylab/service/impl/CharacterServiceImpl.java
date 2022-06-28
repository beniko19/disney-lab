package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.character.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.character.CharacterFiltersDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieBasicDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import com.alkemy.disney.alkemylab.exception.ParamNotFound;
import com.alkemy.disney.alkemylab.mapper.CharacterMapper;
import com.alkemy.disney.alkemylab.repository.CharacterRepository;
import com.alkemy.disney.alkemylab.repository.MovieRepository;
import com.alkemy.disney.alkemylab.repository.specification.CharacterSpecification;
import com.alkemy.disney.alkemylab.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterSpecification characterSpecification;

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        verifyAndAddMovies(dto, entity);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved);
        return result;
    }

    private void verifyAndAddMovies(CharacterDTO dto, CharacterEntity entity) {
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

    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntity2DTOList(entities);
        return result;
    }

    public CharacterDTO getDetailsById(Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid character id");
        CharacterDTO result = characterMapper.characterEntity2DTO(entity.get());
        return result;
    }

    public List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, List<Long> movieId, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movieId, order);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntity2DTOList(entities);
        return dtos;
    }

    @Transactional
    public void delete(Long id) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()  )
            throw new ParamNotFound("Invalid character id");
        characterRepository.deleteByIdCharacter(id);
    }

    public CharacterDTO update(Long id, CharacterDTO character) {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent())
            throw new ParamNotFound("Invalid character id");
        CharacterEntity updatedCharacter = characterMapper.characterDTO2Entity(character);
        entity.get().setAge(updatedCharacter.getAge());
        entity.get().setBackground(updatedCharacter.getBackground());
        entity.get().setName(updatedCharacter.getName());
        entity.get().setImage(updatedCharacter.getImage());
        entity.get().setWeight(updatedCharacter.getWeight());
        characterRepository.save(entity.get());
        CharacterDTO result = characterMapper.characterEntity2DTO(characterRepository.getReferenceById(id));
        return result;
    }
}
