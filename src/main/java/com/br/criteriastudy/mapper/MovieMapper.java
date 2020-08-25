package com.br.criteriastudy.mapper;

import java.util.List;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.services.DTO.MovieDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDTO toDto(Movie movie);
    
    Movie toEntity(MovieDTO movieDto);
    
    List<MovieDTO> toDto(List<Movie> movies);
}