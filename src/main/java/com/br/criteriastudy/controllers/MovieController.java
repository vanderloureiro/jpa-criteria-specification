package com.br.criteriastudy.controllers;

import java.util.List;

import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;
import com.br.criteriastudy.services.MovieService;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "Movie controller")
@CrossOrigin("*")
@RequestMapping("/movie")
@RestController
public class MovieController {
    
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieDTO> postMovie(@RequestBody MovieDTO movieToSave) {
        return ResponseEntity.ok().body(this.movieService.saveMovie(movieToSave));
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies(MovieFilterParam params) {
        return ResponseEntity.ok().body(this.movieService.getAll(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.movieService.getById(id));
    }
}