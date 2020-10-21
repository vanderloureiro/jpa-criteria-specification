package com.br.criteriastudy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import com.br.criteriastudy.entities.CategoryEnum;
import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.MovieRepository;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MovieRepository movieRepository; 

    private Movie movie;

    @Before
    public void start() {
        this.movie = new Movie();
        this.movie.setCategory(CategoryEnum.ACTION);
        this.movie.setDirector("Tarantino");
        this.movie.setSinopse("Django");
        this.movie.setTitle("Django");
        this.movie.setReleaseDate(LocalDate.of(2000, Month.JANUARY, 20));
        this.movie = this.movieRepository.save(this.movie);
    }

    @After
    public void end() {
        this.movieRepository.deleteById(this.movie.getId());
    }

    @Test
    public void getAllMovies() {
        ResponseEntity<MovieDTO[]> response = this.testRestTemplate.exchange("/movie", HttpMethod.GET, null, MovieDTO[].class);
        
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createNewMovie() {
        MovieDTO form = new MovieDTO();
        form.setCategory(CategoryEnum.ACTION);
        form.setDirector("Tarantino");
        form.setSinopse("Kill Bill");
        form.setTitle("Kill Bill");
        form.setReleaseDate(LocalDate.of(2002, Month.JANUARY, 20));
        HttpEntity<MovieDTO> httpEntity = new HttpEntity<>(form);

        ResponseEntity<MovieDTO> response = this.testRestTemplate.exchange("/movie", HttpMethod.POST, httpEntity, MovieDTO.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getTitle(), "Kill Bill");
    }
    
}