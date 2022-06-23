package com.alkemy.disney.alkemylab.service.impl;

import com.alkemy.disney.alkemylab.dto.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.CharacterFiltersDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import com.alkemy.disney.alkemylab.entity.MovieCharacterEntity;
import com.alkemy.disney.alkemylab.mapper.CharacterMapper;
import com.alkemy.disney.alkemylab.mapper.MovieMapper;
import com.alkemy.disney.alkemylab.repository.CharacterRepository;
import com.alkemy.disney.alkemylab.repository.MovieCharacterRepository;
import com.alkemy.disney.alkemylab.repository.specification.CharacterSpecification;
import com.alkemy.disney.alkemylab.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;
    @Autowired
    private MovieMapper movieMapper;

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved);
        if (dto.getMovies() != null)
            addMovies(dto, result);
        return result;
    }

    public List<CharacterDTO> getAllCharacters() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntity2DTOList(entities);
        result.forEach(this::loadMovies);
        return result;
    }

    private void loadMovies(CharacterDTO character) {
        character.setMovies(movieMapper.movieEntity2DTOList(movieCharacterRepository.loadMovies2Character(character.getId())));
    }

    public List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, String movieName, String order) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movieName, order);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntitySet2DTOList(entities);
        dtos.forEach(this::loadMovies);
        return dtos;
    }

    //@Transactional
    public void delete(Long id) {
        movieCharacterRepository.deleteCharacter(id);
        characterRepository.delete(characterRepository.getReferenceById(id));
    }

    public CharacterDTO update(Long id, CharacterDTO character) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(character);
        characterRepository.getReferenceById(id).setImage(entity.getImage());
        characterRepository.getReferenceById(id).setName(entity.getName());
        characterRepository.getReferenceById(id).setWeight(entity.getWeight());
        characterRepository.getReferenceById(id).setAge(entity.getAge());
        characterRepository.getReferenceById(id).setBackground(entity.getBackground());
        characterRepository.save(characterRepository.getReferenceById(id));
        CharacterDTO result = characterMapper.characterEntity2DTO(characterRepository.getReferenceById(id));
        loadMovies(result);
        return result;
    }

    private void addMovies(CharacterDTO dto, CharacterDTO result) {
        result.setMovies(dto.getMovies());
        dto.getMovies().forEach(movie -> {
            MovieCharacterEntity movieCharacter = new MovieCharacterEntity();
            movieCharacter.setMovieId(movie.getId());
            movieCharacter.setCharacterId(result.getId());
            movieCharacterRepository.save(movieCharacter);
        });
        loadMovies(result);
    }
}
