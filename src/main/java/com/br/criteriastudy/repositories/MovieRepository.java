package com.br.criteriastudy.repositories;

import java.util.UUID;

import com.br.criteriastudy.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID>{
    
}