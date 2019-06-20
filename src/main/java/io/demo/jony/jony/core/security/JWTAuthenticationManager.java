package io.demo.jony.jony.core.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import io.demo.jony.jony.core.config.AppContext;
import io.demo.jony.jony.core.dto.LoggedDTO;
import io.demo.jony.jony.model.User;
import io.demo.jony.jony.service.PermissionService;
import io.demo.jony.jony.service.UserService;

/**
 * Authentication Manager.
 *
 * @author Virtus
 *
 */
public class JWTAuthenticationManager implements AuthenticationManager {

    /**
     * User service.
     */
    private UserService userService;

    /**
     * Permission service.
     */
    private PermissionService permissionService;

    /**
     * (non-Javadoc)
     *
     * @see org.springframework.security.authentication.AuthenticationManager#authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        User loggedUser = this.getUserService().login(auth.getName(), (String) auth.getCredentials());

        if (loggedUser != null) {
            return new UsernamePasswordAuthenticationToken(toUserDTO(loggedUser), auth.getCredentials());
        }

        throw new BadCredentialsException("Invalid login and/or password.");
    }
    
    /**
     * Add User's permissions to the DTO.
     * 
     * @param dto
     * 		Logged User DTO.
     * @param idRole
     * 		Role ID.
     * @return DTO with permissions.
     */
    public LoggedDTO addPermissions(LoggedDTO dto, Integer idRole) {
    	dto.setPermissions(new ArrayList<>(getPermissionService().getPermissions(idRole)));
    	return dto;
    }

    /**
     * Gets the User service.
     *
     * @return User service.
     */
    protected UserService getUserService() {

        if (this.userService == null) {
            this.userService = AppContext.getBean(UserService.class);
        }

        return this.userService;
    }
    
    /**
     * Gets the Permission service.
     *
     * @return Permission service.
     */
    protected PermissionService getPermissionService() {
    	
    	if (this.permissionService == null) {
    		this.permissionService = AppContext.getBean(PermissionService.class);
    	}
    	
    	return this.permissionService;
    }
    
    /**
     * Converts the Logged User to DTO.
     *
     * @param User
     *            Logged User.
     * @return DTO.
     */
    private LoggedDTO toUserDTO(User user) {

    	LoggedDTO userDTO = new LoggedDTO();

    	userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setIdRole(user.getRole().getId());
        this.addPermissions(userDTO, user.getRole().getId());

        return userDTO;
    }
}
