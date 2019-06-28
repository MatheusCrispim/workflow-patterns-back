package io.demo.jony.jony.service.workflow.task;

import org.springframework.beans.factory.annotation.Autowired;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.repository.TaskRepository;

public class Adapter implements TaskStateLogic {
	
	@Autowired
	TaskRepository taskRepository;

 	@Override
	public void openOffice(Task task) throws BusinessException {
		throw new BusinessException("Usuario nao pode relizar essa acao");
	}

 	@Override
	public void doBanking(Task task) throws BusinessException {
		throw new BusinessException("Usuario nao pode relizar essa acao");
	}

 	@Override
	public void closeOffice(Task task) throws BusinessException {
		throw new BusinessException("Usuario nao pode relizar essa acao");
	}
 	
}
