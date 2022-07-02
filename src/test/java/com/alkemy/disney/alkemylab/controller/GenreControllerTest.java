package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.dto.genre.GenreDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieBasicDTO;
import com.alkemy.disney.alkemylab.service.GenreService;
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
class GenreControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private GenreService genreService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void getAll() throws Exception {
        GenreDTO dto1 = createGenreDTO(1L, "Action", "action.png", null);
        GenreDTO dto2 = createGenreDTO(2L, "Comedy", "comedy.png", null);
        List<GenreDTO> dtos = Arrays.asList(dto1, dto2);
        when(genreService.getAllGenres()).thenReturn(dtos);
        String request = mapper.writeValueAsString(dtos);
        String content = mockMvc.perform(get("/genres/getAll/")
                .with(httpBasic("beniko", "angelbeast"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(content, request);
        verify(genreService).getAllGenres();
    }

    @Test
    void save() throws Exception {
        GenreDTO dto = createGenreDTO(1L, "Action", "action.png", null);
        String jsonRequest = mapper.writeValueAsString(dto);
        when(genreService.save(any())).thenReturn(dto);
        String content = mockMvc.perform(post("/genres/save/").with(httpBasic("beniko", "angelbeast"))
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L)).andReturn().getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
        verify(genreService).save(dto);
    }

    private GenreDTO createGenreDTO(long id, String name, String image, List<MovieBasicDTO> movies) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(id);
        genreDTO.setImage(image);
        genreDTO.setName(name);
        genreDTO.setMovies(movies);
        return genreDTO;
    }
}