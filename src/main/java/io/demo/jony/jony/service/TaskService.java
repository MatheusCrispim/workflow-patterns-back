package io.demo.jony.jony.service;

import io.demo.jony.jony.core.dto.PageListDTO;
import io.demo.jony.jony.core.dto.SearchFilterDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.workflow.WorkflowLogic;
import io.demo.jony.jony.enums.TaskAction;
import io.demo.jony.jony.enums.TaskLogic;
import io.demo.jony.jony.model.User;
import io.demo.jony.jony.core.workflow.WorkflowService;
import io.demo.jony.jony.service.workflow.task.OfferTaskLogic;
import io.demo.jony.jony.service.workflow.task.GetTaskLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.service.CrudService;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.repository.TaskRepository;

import javax.annotation.PostConstruct;

/**
 * CRUD service of the model: Task.
 *
 * @author Virtus
 */
@Service
public class TaskService extends CrudService<Task, Integer> {

	/**
	 * Task repository.
	 */
	@Autowired
	private TaskRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private WorkflowService<Object, TaskAction, TaskLogic, WorkflowLogic> workflowService;

	 @Autowired
	 private OfferTaskLogic offerTaskLogic;

	 @Autowired
	 private GetTaskLogic getTaskLogic;

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudService#getRepository()
	 */
	@Override
	protected TaskRepository getRepository() {
		return repository;
	}

	@PostConstruct
	public void init() {
		workflowService.map(TaskLogic.offerTask, offerTaskLogic);
		workflowService.map(TaskLogic.getTasks, getTaskLogic);
	}

	@Override
	protected Task preInsert(Task task) throws BusinessException {
		task.setAssignedUser(userService.getOne(getCurrentUserId()));
		return super.preInsert(task);
	}

	public void assignTaskToUser(Integer taskId, User user) throws BusinessException {
		Task task = getOne(taskId);
		workflowService.action(TaskAction.offerTaskToUser,  TaskLogic.offerTask, task, user);
	}

	@Override
	public PageListDTO search(SearchFilterDTO filter) {
		try {
			return (PageListDTO) workflowService.action(TaskAction.getUserTasks, TaskLogic.getTasks, filter,  userService.getOne(getCurrentUserId()));
		} catch (BusinessException e) {
			return super.search(filter);
		}
	}

}