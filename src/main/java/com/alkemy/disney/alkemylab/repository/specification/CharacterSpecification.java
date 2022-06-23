package com.alkemy.disney.alkemylab.repository.specification;

import com.alkemy.disney.alkemylab.dto.CharacterFiltersDTO;
import com.alkemy.disney.alkemylab.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class CharacterSpecification {
    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filtersDTO.getName()!= null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), filtersDTO.getName()));
            }
            if (filtersDTO.getAge() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("age"),filtersDTO.getAge()));
            }
            if (filtersDTO.getWeight() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("weight"), filtersDTO.getWeight()));
            }
            /*if (filtersDTO.getMovies() != null && !CollectionUtils.isEmpty(filtersDTO.getMovies())) {
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }*/

            // Remove duplicates
            query.distinct(true);

            // Order resolver
            if (filtersDTO.getOrder() != null){
                String orderByField = "name";
                query.orderBy(
                        filtersDTO.isASC() ?
                                criteriaBuilder.asc(root.get(orderByField)):
                                criteriaBuilder.desc(root.get(orderByField))
                );
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
