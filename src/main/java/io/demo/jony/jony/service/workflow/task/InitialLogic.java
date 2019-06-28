package io.demo.jony.jony.service.workflow.task;

import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.enums.TaskState;
import io.demo.jony.jony.model.Task;

@Service
public class InitialLogic extends Adapter implements TaskFaseLogic {

	@Override
	public void openOffice(Task task) throws BusinessException {
		task.setTaskState(TaskState.opened);
		taskRepository.save(task);
		return;
	}

}
