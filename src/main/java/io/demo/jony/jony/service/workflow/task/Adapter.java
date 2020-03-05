package io.demo.jony.jony.service.workflow.task;

import io.demo.jony.jony.core.dto.PageListDTO;
import io.demo.jony.jony.core.dto.SearchFilterDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.model.User;
import io.demo.jony.jony.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Adapter implements TaskLogic{

    @Autowired TaskRepository taskRepository;

    @Override
    public void offerTaskToUser(Task task, User user) throws BusinessException{
        throw new BusinessException("Nao foi possivel atribuir a atividade ao usuario");
    }

    @Override
    public PageListDTO getUserTasks(SearchFilterDTO filter, User user) throws BusinessException {
        throw new BusinessException("Nao foi possivel buscar as atividades do usuario");
    }

}
