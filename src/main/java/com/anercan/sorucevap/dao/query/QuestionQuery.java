package com.anercan.sorucevap.dao.query;

import com.anercan.sorucevap.dto.QuestionFilterDto;
import com.anercan.sorucevap.entity.Question;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionQuery implements Specification<Question> {


    @Override
    public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        throw new NotYetImplementedException();
    }

    public Specification<Question> filter(QuestionFilterDto dto) {
        return (Specification<Question>) (root, criteria, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.getStartDate() != null) {
                predicates.add(cb.greaterThan(root.get("date"), dto.getStartDate()));
            }

            if (dto.getEndDate() != null) {
                predicates.add(cb.lessThan(root.get("date"), dto.getEndDate()));
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
