package io.demo.jony.jony.core.model;

import java.io.Serializable;

/**
 * Model representing a table in database.
 * 
 * @author Virtus
 *
 * @param <T> ID's type.
 */
public abstract class Model<T extends Serializable> {

	public static final String WHERE_DELETED_CLAUSE = "deleted = false";
	
	/**
	 * Gets the ID of the model.
	 * 
	 * @return ID of the model.
	 */
	public abstract T getId(); 
	
	/**
	 * Sets the ID of the model.
	 * 
	 * @param id
	 * 		ID of the model.
	 */
	public abstract void setId(T id);

}
