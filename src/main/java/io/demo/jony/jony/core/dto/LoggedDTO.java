package io.demo.jony.jony.core.dto;

import java.util.List;

/**
 * DTO for Logged User.
 *
 * @author Virtus
 */
public class LoggedDTO {

	/**
	 * ID.
	 */
	private Integer id;

    /**
     * login.
     */
    private String login;

    /**
     * Role ID.
     */
    private Integer idRole;

	/**
     * Token.
     */
    private String token;

    /**
     * Refresh token.
     */
    private String refreshToken;
    
    /**
     * Permissions.
     */
    private List<String> permissions;

    /**
     * Constructor.
     */
    public LoggedDTO() {
	}
    
    /**
     * Constructor.
     * 
     * @param id
     * @param login
     */
    public LoggedDTO(Integer id, String login) {
		super();
		
		this.id = id;
		this.login = login;
	}
    
    /**
     * Constructor.
     * 
     * @param id
     * @param login
     * @param idRole
     */
    public LoggedDTO(Integer id, String login, Integer idRole) {
    	super();
    	
    	this.id = id;
    	this.login = login;
    	this.idRole = idRole;
    }

	/**
     * Gets the ID.
     * 
     * @return ID.
     */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the ID.
	 * 
	 * @param id
	 * 		ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
    
    /**
     * Gets the login.
     * 
     * @return Login.
     */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 * 
	 * @param login
	 * 		Login.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
    
    /**
     * Gets the role ID.
     * 
     * @return Role ID.
     */
    public Integer getIdRole() {
		return idRole;
	}

    /**
     * Sets the role ID.
     * 
     * @param role Role ID.
     */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

    /**
     * Gets the token.
     *
     * @return Token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token Token.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets the refresh token.
     *
     * @return Refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Sets the refresh token.
     *
     * @param refreshToken Refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Gets the permissions.
     * 
     * @return Permissions.
     */
	public List<String> getPermissions() {
		return permissions;
	}

	/**
	 * Sets the permissions.
	 * 
	 * @param permissions
	 * 		Permissions.
	 */
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}
