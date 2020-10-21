package com.br.criteriastudy.services;

import java.util.List;
import java.util.Optional;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.exceptions.NotFoundException;
import com.br.criteriastudy.mapper.MovieMapper;
import com.br.criteriastudy.repositories.MovieRepository;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;
import com.br.criteriastudy.services.DTO.MovieDTO;

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

        Movie movieFound = this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException());
        return this.movieMapper.toDto(movieFound);
    }

    public MovieDTO update(Long id, MovieDTO form) {

        this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException());

        Movie movieToUpdate = this.movieMapper.toEntity(form);
        movieToUpdate.setId(id);
        return this.movieMapper.toDto(this.movieRepository.save(movieToUpdate));
    }

    public void deleteById(Long id) {
        this.movieRepository.findById(id).orElseThrow(() -> new NotFoundException());

        this.movieRepository.deleteById(id);
    }
}