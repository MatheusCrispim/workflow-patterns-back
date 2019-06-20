package io.demo.jony.jony.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import io.demo.jony.jony.core.dto.LoggedDTO;

/**
 * The 'BaseService' class provides the common API for all services.
 * <p>
 * All services MUST extend this class.
 *
 * @author Virtus
 */
public abstract class BaseService {

    /**
     * Logger.
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Gets the current User.
     * 
     * @return Current User.
     */
    protected LoggedDTO getCurrentUser() {
        return (LoggedDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    /**
     * Gets the current User login.
     * 
     * @return Login.
     */
    protected String getCurrentLogin() {
    	return getCurrentUser().getLogin();
    }
    
    /**
     * Gets the current User ID.
     * 
     * @return ID.
     */
    protected Integer getCurrentUserId() {
    	return getCurrentUser().getId();
    }
    
    /**
     * Gets the current role ID.
     * 
     * @return ID.
     */
    protected Integer getCurrentIdRole() {
    	return getCurrentUser().getIdRole();
    }
}
