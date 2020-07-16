package com.br.criteriastudy.services.mapper;

import java.util.List;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    
    Movie toEntity(MovieDTO movieDto);
    
    MovieDTO toDto(Movie movie);

    List<MovieDTO> toDto(List<Movie> movieList);
}