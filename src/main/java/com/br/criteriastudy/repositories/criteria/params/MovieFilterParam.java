package com.br.criteriastudy.repositories.criteria.params;

import java.time.LocalDate;

import com.br.criteriastudy.entities.CategoryEnum;

import lombok.Data;

@Data
public class MovieFilterParam {
    
    private String title;
    private CategoryEnum category;
    private String director;
    private LocalDate releaseYear;
    
}