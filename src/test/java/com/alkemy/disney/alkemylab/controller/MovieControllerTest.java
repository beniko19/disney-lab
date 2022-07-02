package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.dto.character.CharacterBasicDTO;
import com.alkemy.disney.alkemylab.dto.genre.GenreBasicDTO;
import com.alkemy.disney.alkemylab.dto.movie.MovieDTO;
import com.alkemy.disney.alkemylab.service.MovieService;
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

import java.time.LocalDate;
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
class MovieControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void getAllTest() throws Exception {
        MovieDTO movie1 = crearmovieDTO(1L, "dbz.img", "Dragon Ball Z", LocalDate.of(1998, 02, 19), 5, null, null);
        MovieDTO movie2 = crearmovieDTO(2L, "naruto.img", "Naruto", LocalDate.of(2000, 10, 29), 4, null, null);
        List<MovieDTO> movies = Arrays.asList(movie1, movie2);
        when(movieService.getAllMovies()).thenReturn(movies);
        String request = mapper.writeValueAsString(movies);
        String response = mockMvc.perform(get("/movies/getAll/").with(httpBasic("beniko", "angelbeast"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(response, request);
    }

    @Test
    void saveTest() throws Exception {
        MovieDTO movie = crearmovieDTO(1L, "dbz.img", "Dragon Ball Z", LocalDate.of(1998, 02, 19), 5, null, null);
        String jsonRequest = mapper.writeValueAsString(movie);
        when(movieService.save(any())).thenReturn(movie);
        String content = mockMvc.perform(post("/movies/save/").with(httpBasic("beniko", "angelbeast"))
                .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1L))
                .andReturn().getResponse().getContentAsString();
        assertEquals(jsonRequest, content);
        verify(movieService).save(any());
    }

    @Test
    void getDetailsByFiltersTest() throws Exception {
        MovieDTO movie = crearmovieDTO(1L, "dbz.img", "Dragon Ball Z", LocalDate.of(1998, 02, 19), 5, null, null);
        List<MovieDTO> movies = Arrays.asList(movie);
        String jsonRequest = mapper.writeValueAsString(movies);
        when(movieService.getByFilters(any(), any(), any())).thenReturn(movies);
        String content = mockMvc.perform(get("/movies/filter/").with(httpBasic("beniko", "angelbeast"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(jsonRequest, content);
    }

    @Test
    void getMovieByIdTest() throws Exception{
        MovieDTO movie = crearmovieDTO(1L, "dbz.img", "Dragon Ball Z", LocalDate.of(1998, 02, 19), 5, null, null);
        when(movieService.getMovieById(any())).thenReturn(movie);
        String request = mapper.writeValueAsString(movie);
        String result = mockMvc.perform(get("/movies/getMovie/1").with(httpBasic("beniko", "angelbeast"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andReturn().getResponse().getContentAsString();
        assertEquals(request, result);
        verify(movieService).getMovieById(any());
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(movieService).delete(any());
        mockMvc.perform(delete("/movies/delete/1").with(httpBasic("beniko", "angelbeast")))
                .andExpect(status().isNoContent());
        verify(movieService).delete(any());
    }

    @Test
    void updateTest() throws Exception{
        MovieDTO movie = crearmovieDTO(1L, "dbz.img", "Dragon Ball Z", LocalDate.of(1998, 02, 19), 5, null, null);
        String jsonRequest = mapper.writeValueAsString(movie);
        when(movieService.update(any(), any())).thenReturn(movie);
        String content = mockMvc.perform(put("/movies/update/1").with(httpBasic("beniko", "angelbeast"))
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(content, jsonRequest);
    }

    private MovieDTO crearmovieDTO(long id, String image, String tittle, LocalDate creationDate, int rating, List<CharacterBasicDTO> characters, List<GenreBasicDTO> genres) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(id);
        movieDTO.setImage(image);
        movieDTO.setTittle(tittle);
        movieDTO.setCreationDate(creationDate);
        movieDTO.setCharacters(characters);
        movieDTO.setGenres(genres);
        return movieDTO;
    }
}