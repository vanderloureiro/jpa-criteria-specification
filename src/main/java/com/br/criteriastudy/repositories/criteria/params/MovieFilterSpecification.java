package com.br.criteriastudy.repositories.criteria.params;

import com.br.criteriastudy.entities.CategoryEnum;
import com.br.criteriastudy.entities.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class MovieFilterSpecification implements Specification<Movie> {

    private String title;
    private CategoryEnum category;

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(this.title)) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + this.title + "%"));
        }
        if (this.category != null) {
            predicates.add(criteriaBuilder.equal(root.get("category"), this.category));
        }

        return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
    }
}
