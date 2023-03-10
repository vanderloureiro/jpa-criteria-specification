package com.br.criteriastudy.controllers;

import java.util.List;

import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;
import com.br.criteriastudy.services.MovieService;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<MovieDTO>> getMovies(@ParameterObject MovieFilterParam params) {
        return ResponseEntity.ok().body(this.movieService.getAll(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {

        try {
            return ResponseEntity.ok().body(this.movieService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO form) {

        try {
            return ResponseEntity.ok().body(this.movieService.update(id, form));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        
        try {            
            this.movieService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}