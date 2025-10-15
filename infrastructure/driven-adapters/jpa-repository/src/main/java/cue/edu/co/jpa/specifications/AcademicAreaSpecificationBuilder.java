package cue.edu.co.jpa.specifications;

import cue.edu.co.jpa.entities.AcademicAreaEntity;
import cue.edu.co.model.academicarea.queries.AcademicAreaPaginationQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static cue.edu.co.jpa.constants.AcademicAreaColumn.NAME;

public class AcademicAreaSpecificationBuilder extends AbstractSpecificationBuilder<AcademicAreaEntity, AcademicAreaPaginationQuery> {

    public AcademicAreaSpecificationBuilder(AcademicAreaPaginationQuery query) {
        super(query);
    }

    public static Specification<AcademicAreaEntity> build(AcademicAreaPaginationQuery query) {
        return new AcademicAreaSpecificationBuilder(query).build();
    }

    @Override
    protected void buildPredicates(List<Predicate> predicates, Root<AcademicAreaEntity> root, CriteriaBuilder cb) {
        addIfPresent(query.name(), predicates, name -> likeIgnoreCase(cb, root.get(NAME), name));
    }
}
