package io.demo.jony.jony.model;

import javax.persistence.*;

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
	@Column(name = "taskstate")
	private TaskState taskState;

	/**
	 * User
	 */
	@ManyToOne
	private User assignedUser;

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
	 * Gets the assignedUser.
	 *
	 * @return assignedUser.
	 */
	public User getAssignedUser() {
		return assignedUser;
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
	 * Sets the assignedUser.
	 *
	 * @param name assignedUser.
	 */
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
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