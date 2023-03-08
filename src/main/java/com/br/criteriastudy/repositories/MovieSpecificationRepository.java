package com.br.criteriastudy.repositories;

import com.br.criteriastudy.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieSpecificationRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
