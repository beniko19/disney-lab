package com.alkemy.disney.alkemylab.repository.specification;

import com.alkemy.disney.alkemylab.dto.MovieFiltersDTO;
import com.alkemy.disney.alkemylab.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Component
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
        return (((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filtersDTO.getTittle() != null)
                predicates.add(criteriaBuilder.equal(root.get("tittle"), filtersDTO.getTittle()));
            if (filtersDTO.getRating() != null)
                predicates.add(criteriaBuilder.equal(root.get("rating"),filtersDTO.getRating()));
            /*if (!filtersDTO.getCharacters().isEmpty() && filtersDTO.getCharacters() != null)
                criteriaBuilder.equal(root.get("characters"), filtersDTO.getCharacters());
            if (!filtersDTO.getGenres().isEmpty() && filtersDTO.getGenres() != null)
                criteriaBuilder.equal(root.get("genres"), filtersDTO.getGenres());*/


            // Remove duplicates
            query.distinct(true);

            // Order resolver
            if (filtersDTO.getOrder() != null) {
                String orderByField = "name";
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
