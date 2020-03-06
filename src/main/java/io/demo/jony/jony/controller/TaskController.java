package io.demo.jony.jony.controller;


import io.demo.jony.jony.core.dto.ModelDTO;
import io.demo.jony.jony.core.dto.PageListDTO;
import io.demo.jony.jony.core.dto.SearchFilterDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.util.JSonUtil;
import io.demo.jony.jony.core.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.demo.jony.jony.core.api.ApiVersion;
import io.demo.jony.jony.core.api.ApiVersions;
import io.demo.jony.jony.core.controller.CrudBaseController;
import io.demo.jony.jony.core.security.authorization.Authorization;
import io.demo.jony.jony.dto.TaskDTO;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.service.TaskService;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public ResponseEntity<TaskDTO> updatePartial(HttpServletRequest request, @PathVariable Integer id,
												 @RequestBody TaskDTO modelDTO) throws Exception {

		Task model = toModel(modelDTO);
		Task dbModel = getService().getOne(id);
		NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);
	  	getService().assignTaskToUser(id, model.getAssignedUser());
		return ok(modelDTO);
	}

	public ResponseEntity<ModelDTO> search(HttpServletRequest request, @RequestParam("filter") String filterJSon)
			throws BusinessException {
		SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
		filter = customSearchFilter(request, filter);

		PageListDTO response = getService().getUserTask(filter);
		response.setItems(toSearchListDTO(response.getItems()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
