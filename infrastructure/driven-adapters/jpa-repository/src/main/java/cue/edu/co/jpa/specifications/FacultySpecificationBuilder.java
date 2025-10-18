package cue.edu.co.jpa.specifications;

import cue.edu.co.jpa.entities.FacultyEntity;
import cue.edu.co.model.faculty.queries.FacultyPaginationQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static cue.edu.co.jpa.constants.FacultyColumn.NAME;

public class FacultySpecificationBuilder extends AbstractSpecificationBuilder<FacultyEntity, FacultyPaginationQuery> {

    public FacultySpecificationBuilder(FacultyPaginationQuery query) {
        super(query);
    }

    public static Specification<FacultyEntity> build(FacultyPaginationQuery query) {
        return new FacultySpecificationBuilder(query).build();
    }

    @Override
    protected void buildPredicates(List<Predicate> predicates, Root<FacultyEntity> root, CriteriaBuilder cb) {
        addIfPresent(query.name(), predicates, name -> likeIgnoreCase(cb, root.get(NAME), name));
    }
}
