package io.demo.jony.jony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.jony.jony.core.api.ApiVersion;
import io.demo.jony.jony.core.api.ApiVersions;
import io.demo.jony.jony.core.controller.CrudBaseController;
import io.demo.jony.jony.core.security.authorization.Authorization;
import io.demo.jony.jony.dto.TaskDTO;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.service.TaskService;
import io.swagger.annotations.Api;

/**
 * CRUD Controller for the model: Task.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("tasks")
@Authorization("task")
@Api(value = "task", tags = "task-controller")
@ApiVersion(ApiVersions.V1)
public class TaskController extends CrudBaseController<Task, Integer, TaskDTO> {

	/**
	 * Task service.
	 */
	@Autowired
	private TaskService service;

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudBaseController#getService()
	 */
	@Override
	protected TaskService getService() {
		return service;
	}

}
