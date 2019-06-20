package io.demo.jony.jony.core.dto;

import java.util.List;

/**
 * DTO for controller operation with list in pageable responses.
 * 
 * @author Virtus
 */
public class PageListDTO extends ModelDTO {
	
	/**
	 * Total of pages.
	 */
	private Integer totalPages;

	/**
	 * List of items.
	 */
	private List<?> items; 
	
	/**
	 * Constructor.
	 */
	public PageListDTO() { }

	/**
	 * Constructor.
	 * 
	 * @param totalPages
	 * 		Total pages.
	 * @param items
	 * 		List of items.
	 */
	public PageListDTO(Integer totalPages, List<?> items) {
		this.totalPages = totalPages; 
		this.items = items;
	}

	/**
	 * Gets the total of pages.
	 * 
	 * @return Total of pages.
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * Gets the list of items.
	 * 
	 * @return List of items.
	 */
	public List<?> getItems() {
		return items;
	}
	
	/**
	 * Sets the list of items.
	 * 
	 * @param items
	 * 		List of items.
	 */
	public void setItems(List<?> items) {
		this.items = items;
	}
}
