package com.br.criteriastudy.controllers;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.mapper.MovieMapper;
import com.br.criteriastudy.repositories.MovieSpecificationRepository;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterSpecification;
import com.br.criteriastudy.services.DTO.MovieDTO;
import jakarta.persistence.EntityManager;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMovieSpecificationController {

    @Autowired
    private EntityManager entityManager;

    public Movie findById(Long id) {

        Movie movie = entityManager.find(Movie.class, id);
        return movie;
    }

    private final MovieSpecificationRepository movieSpecificationRepository;
    private final MovieMapper movieMapper;

    public GetMovieSpecificationController(MovieSpecificationRepository movieSpecificationRepository,
                                           MovieMapper movieMapper) {
        this.movieSpecificationRepository = movieSpecificationRepository;
        this.movieMapper = movieMapper;
    }

    @GetMapping("/movie/specification")
    public ResponseEntity<List<MovieDTO>> execute(@ParameterObject MovieFilterSpecification movieFilterSpecification, @ParameterObject Pageable pageable) {

        Page<Movie> movies = movieSpecificationRepository.findAll(movieFilterSpecification, pageable);
        List<MovieDTO> moviesDto = movieMapper.toDto(movies.getContent());
        return ResponseEntity.ok(moviesDto);
    }
}
