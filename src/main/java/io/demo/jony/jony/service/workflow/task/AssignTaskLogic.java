package io.demo.jony.jony.service.workflow.task;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.model.Task;
import io.demo.jony.jony.model.User;
import org.springframework.stereotype.Service;

@Service
public class AssignTaskLogic extends Adapter implements TaskLogic {

    @Override
    public void assignTaskToUser(Task task, User user) throws BusinessException {
        task.setAssignedUser(user);
        taskRepository.save(task);
    }

}
