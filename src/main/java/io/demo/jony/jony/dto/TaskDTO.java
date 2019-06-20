package io.demo.jony.jony.dto;

import io.demo.jony.jony.core.dto.ModelDTO;
import io.demo.jony.jony.enums.TaskState;

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

}
