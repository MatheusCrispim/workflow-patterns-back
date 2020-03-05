package io.demo.jony.jony.core.workflow;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.service.BaseService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class WorkflowService<D extends Object, A extends Enum<?>, T extends Enum<?>, L extends WorkflowLogic>
        extends BaseService {

    private final Map<T, L> logics = new HashMap<T, L>();

    public void map(T stateField, L logic) {
        logics.put(stateField, logic);
    }

    public Object action(A action, T source, D data, D user) throws BusinessException {
        Method method;
        L logic = logics.get(source);

        try {
            method = logic.getClass().getMethod(action.toString(), data.getClass(), user.getClass());
            Object object = method.invoke(logic, data, user);
            return object;

        } catch (InvocationTargetException e) {
            throw (BusinessException) e.getTargetException();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new BusinessException("Erro desconhecido", LocaleContextHolder.getLocale(), e);
        }
    }

}
