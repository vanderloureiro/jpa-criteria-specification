package com.br.criteriastudy.services;

import java.util.List;
import java.util.Optional;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.MovieRepository;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return this.movieRepository.save(movie);
    }

    public List<Movie> getAll() {
        return this.movieRepository.findAll();
    }

    public Movie getById(Long id) {
        Optional<Movie> opMovie = this.movieRepository.findById(id);
        if (opMovie.isPresent()) {
            return opMovie.get();
        } else { return null; }
    }
}