package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.dto.character.CharacterDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieBasicDTO;
import com.alkemy.disney.alkemylab.service.CharacterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CharacterControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CharacterService characterService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void getCharacterByIdTest() throws Exception {
        CharacterDTO dto = crearCharacterDTO(1L, 23, "goku.img", "Goku", "the saivor of the universe", 45, null);
        when(characterService.getDetailsById(any())).thenReturn(dto);
        mockMvc.perform(get("/characters/getCharacter/1")
                        .with(httpBasic("beniko", "angelbeast"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
        verify(characterService).getDetailsById(1L);
    }
    @Test
    void getAllTest() throws Exception {
        CharacterDTO dto1 = crearCharacterDTO(1L, 23, "goku.img", "Goku", "the saivor of the universe", 45, null);
        CharacterDTO dto2 = crearCharacterDTO(2L, 45, "naruto.img", "Naruto", "A ninja", 47, null);
        List<CharacterDTO> dtos = Arrays.asList(dto1, dto2);
        when(characterService.getAllCharacters()).thenReturn(dtos);
        String jsonRequest = mapper.writeValueAsString(dtos);
        String content = mockMvc.perform(get("/characters/getAll/")
                        .with(httpBasic("beniko", "angelbeast"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }
    @Test
    void saveTest() throws Exception {
        CharacterDTO dto = crearCharacterDTO(1L, 23, "goku.img", "Goku", "the saivor of the universe", 45, null);
        String jsonRequest = mapper.writeValueAsString(dto);
        when(characterService.save(any())).thenReturn(dto);
        String content = mockMvc.perform(post("/characters/save/").with(httpBasic("beniko", "angelbeast"))
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1L)).andReturn().getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
        verify(characterService).save(dto);
    }
    @Test
    void filterTest() throws Exception {
        CharacterDTO dto1 = crearCharacterDTO(1L, 23, "goku.img", "Goku", "The saivor of the universe", 45, null);
        List<CharacterDTO> characterDTOList = new ArrayList<>();
        characterDTOList.add(dto1);
        String jsonRequest = mapper.writeValueAsString(characterDTOList);
        when(characterService.getByFilters(any(), any(), any(), any(), any())).thenReturn(characterDTOList);
        MvcResult result = mockMvc.perform(get("/characters/filter").with(httpBasic("beniko", "angelbeast"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }
    @Test
    void deleteTest() throws Exception {
        doNothing().when(characterService).delete(any());
        mockMvc.perform(delete("/characters/delete/1").with(httpBasic("beniko", "angelbeast")))
                .andExpect(status().isNoContent());
    }
    @Test
    void updateTest() throws Exception {
        CharacterDTO dto1 = crearCharacterDTO(1L, 23, "goku.img", "Goku", "The saivor of the universe", 45, null);
        String jsonRequest = mapper.writeValueAsString(dto1);
        when(characterService.update(any(), any())).thenReturn(dto1);
        String content = mockMvc.perform(put("/characters/update/1").with(httpBasic("beniko", "angelbeast"))
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }
    private CharacterDTO crearCharacterDTO(Long id, int age, String img, String name, String backgournd, int weight, List<MovieBasicDTO> movies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(id);
        dto.setAge(age);
        dto.setImage(img);
        dto.setName(name);
        dto.setBackground(backgournd);
        dto.setWeight(weight);
        dto.setMovies(movies);
        return dto;
    }
}