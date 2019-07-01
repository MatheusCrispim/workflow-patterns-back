package io.demo.jony.jony.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.service.CrudService;
import io.demo.jony.jony.core.workflow.WorkflowService;
import io.demo.jony.jony.enums.TaskState;
import io.demo.jony.jony.enums.TaskStateAction;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.repository.TaskRepository;
import io.demo.jony.jony.service.workflow.task.BankerLogic;
import io.demo.jony.jony.service.workflow.task.ClosedLogic;
import io.demo.jony.jony.service.workflow.task.InitialLogic;
import io.demo.jony.jony.service.workflow.task.OpenLogic;
import io.demo.jony.jony.service.workflow.task.TaskStateLogic;

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
	private WorkflowService<Task, TaskState, TaskStateAction, TaskStateLogic> workflowService;

	@Autowired
	private InitialLogic initialLogic;
	
	@Autowired
	private OpenLogic openedLogic;
	
	@Autowired
	private BankerLogic bankedLogic;
	
	@Autowired
	private ClosedLogic closedLogic;
	
	@PostConstruct
	public void init() {
		workflowService.map(TaskState.initial, initialLogic);
		workflowService.map(TaskState.opened, openedLogic);
		workflowService.map(TaskState.banked, bankedLogic);
		workflowService.map(TaskState.closed, closedLogic);
	}
	
	@Override
	protected Task preInsert(Task model) throws BusinessException {
		model.setTaskState(TaskState.initial);
		model.setDate("10.10.1000");
		return super.preInsert(model);
	}
	
	public void changeState(Integer id, TaskStateAction action) throws BusinessException {
		Task task = getOne(id);
		TaskState sourceState = task.getTaskState();
		workflowService.action(task, sourceState, action);
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudService#getRepository()
	 */
	@Override
	protected TaskRepository getRepository() {
		return repository;
	}

}