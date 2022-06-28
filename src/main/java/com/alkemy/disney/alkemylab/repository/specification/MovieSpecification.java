package com.alkemy.disney.alkemylab.repository.specification;

import com.alkemy.disney.alkemylab.dto.movie.MovieFiltersDTO;
import com.alkemy.disney.alkemylab.entity.GenreEntity;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getTittle())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("tittle")),
                                "%" + filtersDTO.getTittle().toLowerCase() + "%"));
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getGenres())) {
                Join<GenreEntity, MovieEntity> join = root.join("genres", JoinType.INNER);
                Expression<String> genresId = join.get("id");
                predicates.add(genresId.in(filtersDTO.getGenres()));
            }


            // Remove duplicates
            query.distinct(true);

            // Order resolver
            if (filtersDTO.getOrder() != null) {
                String orderByField = "tittle";
                query.orderBy(
                        filtersDTO.isASC() ?
                                criteriaBuilder.asc(root.get(orderByField)):
                                criteriaBuilder.desc(root.get(orderByField))
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }));
    }
}
