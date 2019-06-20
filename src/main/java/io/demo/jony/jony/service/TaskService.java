package io.demo.jony.jony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.service.CrudService;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.repository.TaskRepository;

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