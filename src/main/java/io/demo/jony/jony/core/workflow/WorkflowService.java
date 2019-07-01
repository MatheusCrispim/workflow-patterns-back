package io.demo.jony.jony.core.workflow;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.model.Model;
import io.demo.jony.jony.core.security.SecurityService;
import io.demo.jony.jony.core.service.BaseService;

@Component
public class WorkflowService<E extends Model<?>, F extends Enum<?>, A extends Enum<?>, L extends WorkflowLogic>
		extends BaseService {

	private final Map<F, L> logics = new HashMap<F, L>();
	
	@Autowired
	private SecurityService securityService;

	public void map(F stateField, L logic) {
		logics.put(stateField, logic);
	}

	public void action(E entity, F sourceState, A action) throws BusinessException {
		Method method;
		L logic = logics.get(sourceState);
		try {
			method = logic.getClass().getMethod(action.toString(), entity.getClass());
			
			UsersAllowed usersAllowed = method.getAnnotation(UsersAllowed.class);
			if (usersAllowed != null && !isAllowed(getCurrentLogin(), usersAllowed.value())) {
				throw new BusinessException("Usuario nao pode relizar essa acao");
			}
			
			RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
			if (rolesAllowed != null && !securityService.allowOnRole(rolesAllowed.value())) {
				throw new BusinessException("Usuario nao pode relizar essa acao");
			}
			
			method.invoke(logic, entity);
		} catch (InvocationTargetException e) {
			throw (BusinessException) e.getTargetException();
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
			throw new BusinessException("Erro desconhecido", LocaleContextHolder.getLocale(), e);
		}
	}
	
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	private boolean isAllowed(String loginUser, String[] loginsAutorizados) {
		for (String login : loginsAutorizados) {
			if (login.equals(loginUser))
				return true;
		}
		return false;
	}

}
