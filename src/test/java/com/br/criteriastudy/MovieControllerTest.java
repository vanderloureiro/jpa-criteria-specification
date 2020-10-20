package com.br.criteriastudy;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.MovieRepository;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieRepository movieRepository; 

    private MovieDTO movie;

    @Before
    public void start() {
        this.movie = new MovieDTO();
    }

    @After
    public void end() {
        this.movieRepository.deleteAll();
    }
    
}