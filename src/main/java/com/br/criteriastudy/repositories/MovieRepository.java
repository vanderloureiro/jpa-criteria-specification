package com.br.criteriastudy.repositories;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.criteria.MovieRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRepositoryCustom {
    
}
