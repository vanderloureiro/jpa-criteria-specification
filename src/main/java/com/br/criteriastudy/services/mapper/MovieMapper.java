package com.br.criteriastudy.services.mapper;

import java.util.ArrayList;
import java.util.List;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    
    public Movie toEntity(MovieDTO movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setSinopse(movieDto.getSinopse());
        movie.setCategory(movieDto.getCategory());
        movie.setDirector(movieDto.getDirector());
        movie.setReleaseDate(movieDto.getReleaseDate());
        return movie;
    }
    
    public MovieDTO toDto(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setSinopse(movie.getSinopse());
        dto.setCategory(movie.getCategory());
        dto.setDirector(movie.getDirector());
        dto.setReleaseDate(movie.getReleaseDate());
        return dto;
    }

    public List<MovieDTO> toDto(List<Movie> movieList) {
        List<MovieDTO> dtos = new ArrayList<>();
        movieList.forEach(movie -> {
            dtos.add(this.toDto(movie));
        });
        return dtos;
    }
}