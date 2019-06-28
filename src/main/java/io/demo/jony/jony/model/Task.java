package io.demo.jony.jony.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import io.demo.jony.jony.core.model.Model;
import io.demo.jony.jony.enums.TaskState;

/**
 * Model for table: TASK.
 *
 * @author Virtus
 */
@Entity(name = "task")
@SQLDelete(sql = "UPDATE task SET deleted = true WHERE id = ?")
@Where(clause = Model.WHERE_DELETED_CLAUSE)
public class Task extends Model<Integer> {

	/**
	 * Task ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/**
	 * Date.
	 */
	@Column(name = "date")
	private String date;

	/**
	 * State.
	 */
	@Enumerated(EnumType.STRING)
	private TaskState taskState;

	/**
	 * If it is deleted.
	 */
	@Column(name = "deleted")
	private boolean deleted = false;

	public Task() {
	}

	/**
	 * Gets the date.
	 *
	 * @return date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Gets the taskState.
	 *
	 * @return taskState.
	 */
	public TaskState getTaskState() {
		return taskState;
	}

	/**
	 * Sets the date.
	 *
	 * @param name date.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Sets the taskState.
	 *
	 * @param name taskState.
	 */
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}

	/**
	 * Gets if it is deleted.
	 * 
	 * @return If it is deleted.
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets if it is deleted.
	 * 
	 * @param deleted If it is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see Model#getId()
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see Model#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}