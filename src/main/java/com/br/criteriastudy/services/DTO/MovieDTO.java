package com.br.criteriastudy.services.DTO;

import java.time.LocalDate;

import com.br.criteriastudy.entities.CategoryEnum;

import lombok.Data;

@Data
public class MovieDTO {
    
    private Long id;
    private String title;
    private String sinopse;
    private CategoryEnum category;
    private String director;
    private LocalDate releaseDate;

}
