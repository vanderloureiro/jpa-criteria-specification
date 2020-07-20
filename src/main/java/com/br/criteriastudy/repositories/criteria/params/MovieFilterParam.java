package com.br.criteriastudy.repositories.criteria.params;

import java.time.LocalDate;

import com.br.criteriastudy.entities.CategoryEnum;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class MovieFilterParam {
    
    private String title;
    private CategoryEnum category;
    private String director;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate releaseDate;
    
}