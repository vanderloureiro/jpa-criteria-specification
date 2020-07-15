package com.br.criteriastudy.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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