package com.br.criteriastudy.repositories.criteria.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
        if ( params.getCategory() != null) {
            predicates.add(criteriaBuilder.equal(movie.get("category"), params.getCategory()));
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
            query.where(predicates.toArray(new Predicate[predicates.size()]));
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