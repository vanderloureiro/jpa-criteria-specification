package com.br.criteriastudy.repositories.criteria.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.br.criteriastudy.entities.Movie;
import com.br.criteriastudy.repositories.criteria.MovieCriteriaCustom;
import com.br.criteriastudy.repositories.criteria.params.MovieFilterParam;

public class MovieCriteriaImpl implements MovieCriteriaCustom {

    private final EntityManager entityManager;

    public MovieCriteriaImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Movie> getWithFilter(MovieFilterParam params) {
        /**
         * First, we get a CriteriaBuilder reference, which we can use to create
         * different parts of the query
         */
        final CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        /**
         * Using the CriteriaBuilder, we create a CriteriaQuery<Book>, which describes
         * what we want to do in the query. Also, it declares the type of a row in the
         * result
         */
        final CriteriaQuery<Movie> query = criteriaBuilder.createQuery(Movie.class);
        /**
         * With CriteriaQuery<Book> we declare the starting point of the query (Book
         * entity), and we store it in the book variable for later use
         */
        final Root<Movie> movie = query.from(Movie.class);
        /**
         * Next, with CriteriaBuilder we create predicates against our Book entity.
         * Note, that these predicates don't have any effect yet
         */
        final Predicate titlePredicate = criteriaBuilder.like(movie.get("title"), "%" + params.getTitle() + "%");
        final Predicate categoryePredicate = criteriaBuilder.equal(movie.get("category"), params.getCategory());
        /**
         * We apply both predicates to our CriteriaQuery.
         * CriteriaQuery.where(Predicate…) combines its arguments in a logical and. This
         * is the point when we tie these predicates to the query
         */
        query.where(titlePredicate, categoryePredicate);
        /**
         * After that, we create a TypedQuery<Book> instance from our CriteriaQuery
         */
        final TypedQuery<Movie> queryResult = this.entityManager.createQuery(query);
        /** 
         * Finally, we return all matching Book entities
        */
        return queryResult.getResultList();
    }
    
}