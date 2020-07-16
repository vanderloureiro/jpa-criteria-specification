package com.br.criteriastudy.services;

import java.util.List;
import java.util.Optional;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.MovieRepository;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;
import com.br.criteriastudy.services.DTO.MovieDTO;
import com.br.criteriastudy.services.mapper.MovieMapper;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    private MovieRepository movieRepository;
    private MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public MovieDTO saveMovie(MovieDTO movie) {
        Movie movieToSave = this.movieMapper.toEntity(movie);
        return this.movieMapper.toDto(this.movieRepository.save(movieToSave));
    }

    public List<MovieDTO> getAll(MovieFilterParam params) {
        return this.movieMapper.toDto(this.movieRepository.getWithFilter(params));
    }

    public MovieDTO getById(Long id) {
        Optional<Movie> opMovie = this.movieRepository.findById(id);
        if (opMovie.isPresent()) {
            return this.movieMapper.toDto(opMovie.get());
        } else { return null; }
    }
}