package io.demo.jony.jony.service.workflow.task;

import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.enums.TaskState;
import io.demo.jony.jony.model.Task;

@Service
public class OpenedLogic extends Adapter implements TaskStateLogic {

 	@Override
	public void doBanking(Task task) throws BusinessException {
		task.setTaskState(TaskState.banked);
		taskRepository.save(task);
		return;
	}

}