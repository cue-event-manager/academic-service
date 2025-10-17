package cue.edu.co.jpa.specifications;

import cue.edu.co.jpa.entities.AcademicProgramEntity;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static cue.edu.co.jpa.constants.AcademicProgramColumn.FACULTY_ID;
import static cue.edu.co.jpa.constants.AcademicProgramColumn.NAME;

public class AcademicProgramSpecificationBuilder extends AbstractSpecificationBuilder<AcademicProgramEntity, AcademicProgramPaginationQuery> {

    public AcademicProgramSpecificationBuilder(AcademicProgramPaginationQuery query) {
        super(query);
    }

    public static Specification<AcademicProgramEntity> build(AcademicProgramPaginationQuery query) {
        return new AcademicProgramSpecificationBuilder(query).build();
    }

    @Override
    protected void buildPredicates(List<Predicate> predicates, Root<AcademicProgramEntity> root, CriteriaBuilder cb) {
        addIfPresent(query.name(), predicates, name -> likeIgnoreCase(cb, root.get(NAME), name));
        addIfPresent(query.facultyId(), predicates, facultyId -> cb.equal(root.get("faculty").get("id"), facultyId));
    }
}
