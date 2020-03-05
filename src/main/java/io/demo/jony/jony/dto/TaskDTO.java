package io.demo.jony.jony.dto;

import io.demo.jony.jony.core.dto.ModelDTO;
import io.demo.jony.jony.enums.TaskAction;
import io.demo.jony.jony.enums.TaskState;
import io.demo.jony.jony.model.User;

/**
 * DTO for Model: Task.
 *
 * @author Virtus
 */
public class TaskDTO extends ModelDTO {

	private Integer id;

	/**
	 * Date.
	 */
	private String date;

	/**
	 * State.
	 */
	private TaskState taskState;

	/**
	 * State.
	 */
	private TaskAction taskAction;

	/**
	 * User.
	 */
	private UserDTO assignedUser;


	public TaskDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	 * Gets the taskAction.
	 *
	 * @return taskAction.
	 */
	public TaskAction getTaskAction() {
		return taskAction;
	}

	/**
	 * Gets the assignedUser.
	 *
	 * @return assignedUser.
	 */
	public UserDTO getAssignedUser() {
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
	 * Sets the taskAction.
	 *
	 * @param name taskAction.
	 */
	public void setTaskAction(TaskAction taskAction){
		this.taskAction = taskAction;
	}

	/**
	 * Sets the assignedUser.
	 *
	 * @param name assignedUser.
	 */
	public void setAssignedUser(UserDTO assignedUser){
		this.assignedUser = assignedUser;
	}

}
