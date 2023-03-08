package com.br.criteriastudy.repositories.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.criteria.MovieRepositoryCustom;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;

public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    private EntityManager entityManager;

    public MovieRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Movie> getWithFilter(MovieFilterParam params) {
        /**
         * First, we get a CriteriaBuilder reference, which we can use to create
         * different parts of the query
         */
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        /**
         * Using the CriteriaBuilder, we create a CriteriaQuery<Movie>, which describes
         * what we want to do in the query. Also, it declares the type of a row in the
         * result
         */
        CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        /**
         * With CriteriaQuery<Movie> we declare the starting point of the query (Movie
         * entity), and we store it in the Movie variable for later use
         */
        Root<Movie> movie = query.from(Movie.class);

        /**
         * Create a dinamic list of predicates
         */
        List<Predicate> predicates = new ArrayList<>();
        /**
         * Next, with CriteriaBuilder we create predicates against our Movie entity.
         * Note, that these predicates don't have any effect yet
         */

        if ( params.getTitle() != null) {
            predicates.add(criteriaBuilder.like(movie.get("title"), "%" + params.getTitle() + "%"));
        }
        if ( params.getDirector() != null) {
            predicates.add(criteriaBuilder.like(movie.get("director"), "%" + params.getTitle() + "%"));
        }
        if ( params.getCategory() != null) {
            predicates.add(criteriaBuilder.equal(movie.get("category"), params.getCategory()));
        }
        if ( params.getReleaseDate() != null) {
            predicates.add(criteriaBuilder.equal(movie.get("releaseDate"), params.getReleaseDate()));
        }
        /**
         * We apply both predicates to our CriteriaQuery.
         * CriteriaQuery.where(Predicate…) combines its arguments in a logical and. This
         * is the point when we tie these predicates to the query
         */

        /**
        Verify the predicates to add in where clause
        */
        if (!predicates.isEmpty()) {
            query.where( predicates.stream().toArray( Predicate[]::new ) );
        }
        /**
         * After that, we create a TypedQuery<Movie> instance from our CriteriaQuery
         */
        TypedQuery<Movie> queryResult = this.entityManager.createQuery(query);
        /** 
         * Finally, we return all matching Movie entities
        */
        return queryResult.getResultList();
    }
    
}