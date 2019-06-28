package io.demo.jony.jony.service.workflow.task;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.workflow.WorkflowLogic;
import io.demo.jony.jony.model.Task;

public interface TaskStateLogic extends WorkflowLogic {
	
	public void openOffice(Task task) throws BusinessException;

 	public void doBanking(Task task) throws BusinessException;

 	public void closeOffice(Task task) throws BusinessException;
 	
}
