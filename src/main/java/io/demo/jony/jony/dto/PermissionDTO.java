package io.demo.jony.jony.dto;

import io.demo.jony.jony.core.dto.ModelDTO;

/**
 * DTO for Model: Permission.
 *
 * @author Virtus
 */
public class PermissionDTO extends ModelDTO {

	private Integer id;

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Description.
	 */
	private String description;

	public PermissionDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the description.
	 *
	 * @return description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the name.
	 *
	 * @param name name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description.
	 *
	 * @param name description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
