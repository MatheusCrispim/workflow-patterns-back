package io.demo.jony.jony.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.jony.jony.core.api.ApiVersion;
import io.demo.jony.jony.core.api.ApiVersions;
import io.demo.jony.jony.core.controller.CrudBaseController;
import io.demo.jony.jony.core.security.authorization.Authorization;
import io.demo.jony.jony.core.util.NullAwareBeanUtils;
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
	
	public ResponseEntity<TaskDTO> updatePartial(HttpServletRequest request, @PathVariable Integer id,
			@RequestBody TaskDTO modelDTO) throws Exception {
		Task model = toModel(modelDTO);
		Task dbModel = getService().getOne(id);
		NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);
		getService().changeState(id, modelDTO.getTaskStateAction());
		return ok(modelDTO);
	}


}
