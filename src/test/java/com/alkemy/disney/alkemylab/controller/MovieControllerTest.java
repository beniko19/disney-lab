package com.alkemy.disney.alkemylab.controller;

import com.alkemy.disney.alkemylab.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

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
    void getAllTest() {
    }

    @Test
    void saveTest() {
    }

    @Test
    void getDetailsByFiltersTest() {
    }

    @Test
    void getMovieByIdTest() {
    }

    @Test
    void deleteTest() {
    }

    @Test
    void addCharactersToMovieTest() {
    }

    @Test
    void removeCharactersFromMovieTest() {
    }

    @Test
    void updateTest() {
    }
}