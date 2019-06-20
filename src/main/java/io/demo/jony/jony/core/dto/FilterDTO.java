package io.demo.jony.jony.core.dto;

import io.demo.jony.jony.core.domain.Comparison;

/**
 * DTO for filtering the searches.
 * 
 * @author Virtus
 *
 */
public class FilterDTO {

	/**
	 * Field.
	 */
    private String field;
    
    /**
     * Value.
     */
    private Object value;
    
    /**
     * Comparison.
     */
    private Comparison comparison;

    /**
     * Constructor.
     * 
     * @param key Key.
     * @param value Value.
     * @param comparison Comparison.
     */
    public FilterDTO(String key, Object value, Comparison comparison) {
        this.field = key;
        this.value = value;
        this.comparison = comparison;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }
}
