package io.demo.jony.jony.service.workflow.task;

import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.workflow.RolesAllowed;
import io.demo.jony.jony.enums.TaskState;
import io.demo.jony.jony.model.Task;

@Service
public class OpenLogic extends Adapter implements TaskStateLogic {

 	@Override
 	@RolesAllowed("manager")
	public void doBanking(Task task) throws BusinessException {
		task.setTaskState(TaskState.banked);
		taskRepository.save(task);
		return;
	}

}
