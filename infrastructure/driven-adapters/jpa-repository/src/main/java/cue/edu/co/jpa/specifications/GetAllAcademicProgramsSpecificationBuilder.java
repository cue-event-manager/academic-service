package cue.edu.co.jpa.specifications;

import cue.edu.co.jpa.entities.AcademicProgramEntity;
import cue.edu.co.model.academicprogram.queries.AcademicProgramPaginationQuery;
import cue.edu.co.model.academicprogram.queries.GetAllAcademicProgramsQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static cue.edu.co.jpa.constants.AcademicProgramColumn.NAME;

public class GetAllAcademicProgramsSpecificationBuilder extends AbstractSpecificationBuilder<AcademicProgramEntity, GetAllAcademicProgramsQuery> {

    public GetAllAcademicProgramsSpecificationBuilder(GetAllAcademicProgramsQuery query) {
        super(query);
    }

    public static Specification<AcademicProgramEntity> build(GetAllAcademicProgramsQuery query) {
        return new GetAllAcademicProgramsSpecificationBuilder(query).build();
    }

    @Override
    protected void buildPredicates(List<Predicate> predicates, Root<AcademicProgramEntity> root, CriteriaBuilder cb) {
        addIfPresent(query.name(), predicates, name -> likeIgnoreCase(cb, root.get(NAME), name));
        addIfPresent(query.facultyId(), predicates, facultyId -> cb.equal(root.get("faculty").get("id"), facultyId));
    }
}
