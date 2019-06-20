package io.demo.jony.jony.core.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import io.demo.jony.jony.core.model.Model;

/**
 * Search specification for filtering the items.
 *
 * @param <M> Model type.
 * @param <T> ID type.
 * @author Virtus
 */
public class SearchSpecification<M extends Model<T>, T extends Serializable> implements Specification<M> {

    /**
	 * Default Serial Version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Conditions.
     */
    private List<Condition> conditions;

    /**
     * Input to search.
     */
    private String input;

    /**
     * Constructor.
     *
     * @param conditions Conditions.
     * @param input      Input.
     */
    public SearchSpecification(List<Condition> conditions, String input) {
        super();

        this.conditions = conditions;
        this.input = input;
    }

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
     */
    @Override
    public Predicate toPredicate(Root<M> root, CriteriaQuery<?> cq, CriteriaBuilder builder) {
        List<Predicate> predicates = buildPredicates(root, builder);

        if (input != null) {
            predicates.add(allFields(root, builder));
        }

        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    /**
     * Builds all predicates by the conditions specified.
     *
     * @param root            Root model.
     * @param criteriaBuilder Criteria builder.
     * @return
     */
    private List<Predicate> buildPredicates(Root<M> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach(condition ->
                predicates.add(predicate(condition, root, criteriaBuilder))
        );
        return predicates;
    }

    /**
     * Adds all fields to the search.
     *
     * @param root
     * @param builder
     * @return Predicate with all fields.
     */
    private Predicate allFields(Root<M> root, CriteriaBuilder builder) {
        return builder.or(
                root.getModel().getDeclaredSingularAttributes().stream()
                        .filter(a -> a.getJavaType().getSimpleName().equalsIgnoreCase("string"))
                        .map(attr -> builder.like(builder.lower(root.get(attr.getName())), likeValue(input.toLowerCase())))
                        .toArray(Predicate[]::new)
        );
    }

    /**
     * Creates the predicate from a condition.
     *
     * @param condition
     * @param root
     * @param criteriaBuilder
     * @return Predicate.
     */
    private Predicate predicate(Condition condition, Root<M> root, CriteriaBuilder criteriaBuilder) {
        try {
            switch (condition.getComparison()) {
                case LIKE:
                    return like(condition, root, criteriaBuilder);
                case IN:
                    return in(condition, root, criteriaBuilder);
                default:
                    return equals(condition, root, criteriaBuilder);
            }
        } catch (NullPointerException ex) {
            return equals(condition, root, criteriaBuilder);
        }
    }

    /**
     * Creates the EQUALS predicate.
     *
     * @param condition       Condition.
     * @param root
     * @param criteriaBuilder
     * @return Equals predicate.
     */
    private Predicate equals(Condition condition, Root<M> root, CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.equal(root.get(condition.getField()), condition.getValue());
    }

    /**
     * Creates the LIKE predicate.
     *
     * @param condition
     * @param root
     * @param criteriaBuilder
     * @return Like predicate.
     */
    private Predicate like(Condition condition, Root<M> root, CriteriaBuilder criteriaBuilder) {

        String conditionValue = (String) condition.getValue();
        return criteriaBuilder.like(criteriaBuilder.lower(root.get(condition.getField())), likeValue(conditionValue.toLowerCase()));
    }

    /**
     * Creates the IN predicate.
     *
     * @param condition
     * @param root
     * @param criteriaBuilder
     * @return IN predicate.
     */
    private Predicate in(Condition condition, Root<M> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (String value : condition.getTextValue().split(",")) {
            condition.setValue("," + value + ",");
            predicates.add(like(condition, root, criteriaBuilder));
        }

        Predicate predicate = predicates.size() > 1
                ? criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))
                : predicates.get(0);

        return criteriaBuilder.and(predicate);
    }

    /**
     * Format the value for a like operation. <br>
     * Ex: %VALUE%.
     *
     * @param value Value.
     * @return Value formatted for a like operation.
     */
    private String likeValue(String value) {
        return String.format("%%%s%%", value);
    }
}
