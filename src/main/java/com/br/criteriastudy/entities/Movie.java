package com.br.criteriastudy.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Table(name = "movie")
@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(nullable = false)
    private String sinopse;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false, length = 120)
    private String director;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

}