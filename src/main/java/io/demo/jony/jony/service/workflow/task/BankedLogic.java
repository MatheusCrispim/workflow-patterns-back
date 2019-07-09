package io.demo.jony.jony.service.workflow.task;

import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.enums.TaskState;
import io.demo.jony.jony.model.Task;

@Service
public class BankedLogic extends Adapter implements TaskStateLogic {

 	@Override
	public void closeOffice(Task task) throws BusinessException {
		task.setTaskState(TaskState.closed);
		taskRepository.save(task);
		return;
	}

 } 