package com.br.criteriastudy.controllers;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.mapper.MovieMapper;
import com.br.criteriastudy.repositories.MovieSpecificationRepository;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterSpecification;
import com.br.criteriastudy.services.DTO.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetMovieSpecificationController {

    private final MovieSpecificationRepository movieSpecificationRepository;
    private final MovieMapper movieMapper;

    public GetMovieSpecificationController(MovieSpecificationRepository movieSpecificationRepository,
                                           MovieMapper movieMapper) {
        this.movieSpecificationRepository = movieSpecificationRepository;
        this.movieMapper = movieMapper;
    }

    // https://www.baeldung.com/rest-api-search-language-spring-data-specifications
    // https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl
    // https://www.youtube.com/watch?v=1bTg0tEJAqQ&ab_channel=DevEficiente
    @GetMapping("/movie/specification")
    public ResponseEntity<List<MovieDTO>> execute(MovieFilterSpecification movieFilterSpecification, Pageable pageable) {

        Page<Movie> movies = movieSpecificationRepository.findAll(movieFilterSpecification, pageable);
        List<MovieDTO> moviesDto = movieMapper.toDto(movies.getContent());
        return ResponseEntity.ok(moviesDto);
    }
}
