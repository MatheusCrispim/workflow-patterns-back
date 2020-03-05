package io.demo.jony.jony.service.workflow.task;

import io.demo.jony.jony.core.domain.Comparison;
import io.demo.jony.jony.core.dto.FilterDTO;
import io.demo.jony.jony.core.dto.PageListDTO;
import io.demo.jony.jony.core.dto.SearchFilterDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.model.User;
import org.springframework.stereotype.Service;


@Service
public class GetTaskLogic extends Adapter implements TaskLogic {

    @Override
    public PageListDTO getUserTasks(SearchFilterDTO filter, User user) throws BusinessException{
        FilterDTO filterDTO = new FilterDTO("assignedUser", user, Comparison.EQ);
        filter.getFilters().add(filterDTO);
        return taskRepository.search(filter);
    }

}
