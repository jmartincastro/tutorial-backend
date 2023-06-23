package com.ccsw.tutorial.loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.loan.model.Loan;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LoanSpecification implements Specification<Loan> {

    private static final long serialVersionUID = 1L;

    private final SearchCriteria criteria;

    public LoanSpecification(SearchCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Loan> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(":") && criteria.getValue() != null) {
            Path<String> path = getPath(root);
            if (path.getJavaType() == String.class) {
                return builder.like(path, "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(path, criteria.getValue());
            }
        }

        if (criteria.getOperation().equalsIgnoreCase("between") && criteria.getValue() != null) {
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(criteria.getValue().toString());
                return null;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (criteria.getOperation().equalsIgnoreCase("<") && criteria.getValue() != null) {
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(criteria.getValue().toString());
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Path<String> getPath(Root<Loan> root) {
        String key = criteria.getKey();
        String[] split = key.split("[.]", 0);

        Path<String> expression = root.get(split[0]);
        for (int i = 1; i < split.length; i++) {
            expression = expression.get(split[i]);
        }

        return expression;
    }

}