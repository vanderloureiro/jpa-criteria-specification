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

    private MovieDTO form;

    @Before
    public void start() {
        this.movie = new Movie();
        this.movie.setCategory(CategoryEnum.ACTION);
        this.movie.setDirector("Tarantino");
        this.movie.setSinopse("Django");
        this.movie.setTitle("Django");
        this.movie.setReleaseDate(LocalDate.of(2000, Month.JANUARY, 20));
        this.movie = this.movieRepository.save(this.movie);

        this.form = new MovieDTO();
        this.form.setCategory(CategoryEnum.ACTION);
        this.form.setDirector("Tarantino");
        this.form.setSinopse("Kill Bill");
        this.form.setTitle("Kill Bill");
        this.form.setReleaseDate(LocalDate.of(2002, Month.JANUARY, 20));
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

        HttpEntity<MovieDTO> httpEntity = new HttpEntity<>(form);

        ResponseEntity<MovieDTO> response = this.testRestTemplate.exchange("/movie", HttpMethod.POST, httpEntity, MovieDTO.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getTitle(), "Kill Bill");
    }

    @Test
    public void successGettingMovieById() {
        ResponseEntity<MovieDTO> response = this.testRestTemplate
            .exchange("/movie/"+this.movie.getId(), HttpMethod.GET, null, MovieDTO.class);
        
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void notFoundMovieById() {
        ResponseEntity<MovieDTO> response = this.testRestTemplate
            .exchange("/movie/"+105452L, HttpMethod.GET, null, MovieDTO.class);
        
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateMovie() {

        MovieDTO newForm = this.form;
        newForm.setSinopse("New sinopse");
        HttpEntity<MovieDTO> httpEntity = new HttpEntity<>(newForm);

        ResponseEntity<MovieDTO> response = this.testRestTemplate
            .exchange("/movie/"+this.movie.getId(), HttpMethod.PUT, httpEntity, MovieDTO.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getSinopse(), "New sinopse");
    }

    @Test
    public void errorUpdateMovie() {

        MovieDTO newForm = this.form;
        newForm.setSinopse("New sinopse");
        HttpEntity<MovieDTO> httpEntity = new HttpEntity<>(newForm);

        ResponseEntity<MovieDTO> response = this.testRestTemplate
            .exchange("/movie/"+25482L, HttpMethod.PUT, httpEntity, MovieDTO.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void successDeleteMovieById() {

        Movie savedMovie = this.movieRepository.save(this.movie);

        ResponseEntity<MovieDTO> response = this.testRestTemplate
            .exchange("/movie/"+savedMovie.getId(), HttpMethod.DELETE, null, MovieDTO.class);
        
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void notFoundDeleteMovieById() {
        ResponseEntity<MovieDTO> response = this.testRestTemplate
            .exchange("/movie/"+105452L, HttpMethod.DELETE, null, MovieDTO.class);
        
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}