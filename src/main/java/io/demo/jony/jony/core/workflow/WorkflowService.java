package io.demo.jony.jony.core.workflow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.model.Model;
import io.demo.jony.jony.core.service.BaseService;

public class WorkflowService<E extends Model<?>, F extends Enum<?>, A extends Enum<?>, L extends WorkflowLogic>
		extends BaseService {

	private final Map<F, L> logics = new HashMap<F, L>();

	public void map(F stateField, L logic) {
		logics.put(stateField, logic);
	}

	public void action(E entity, F sourceState, A action) throws BusinessException {
		Method method;
		L logic = logics.get(sourceState);
		try {
			method = logic.getClass().getMethod(action.toString(), entity.getClass());
			method.invoke(logic, entity);
		} catch (InvocationTargetException e) {
			throw (BusinessException) e.getTargetException();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
			throw new BusinessException("Erro desconhecido", LocaleContextHolder.getLocale(), e);
		}
	}

}
