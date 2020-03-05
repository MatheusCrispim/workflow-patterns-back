package io.demo.jony.jony.service.workflow.task;

import io.demo.jony.jony.core.dto.PageListDTO;
import io.demo.jony.jony.core.dto.SearchFilterDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.workflow.WorkflowLogic;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.model.User;


public interface TaskLogic extends WorkflowLogic {

    public void assignTaskToUser(Task task, User user) throws BusinessException;

    public PageListDTO getUserTasks(SearchFilterDTO filter, User user) throws BusinessException;

}
