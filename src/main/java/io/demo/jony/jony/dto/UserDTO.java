package io.demo.jony.jony.dto;

import io.demo.jony.jony.core.dto.ModelDTO;

/**
 * DTO for Model: User.
 *
 * @author Virtus
 */
public class UserDTO extends ModelDTO {

	/**
	 * User ID.
	 */
	private Integer id;

	/**
	 * Name.
	 */
	private String name;

	/**
	 * Email.
	 */
	private String email;

	/**
	 * Login.
	 */
	private String login;

	/**
	 * Role.
	 */
	private RoleDTO role;

	/**
	 * New Password.
	 */
	private String newPassword;

	/**
	 * Current Password.
	 */
	private String currentPassword;

	/**
	 * Gets the name.
	 *
	 * @return name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the email.
	 *
	 * @return email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the login.
	 *
	 * @return login.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Gets the role.
	 *
	 * @return role.
	 */
	public RoleDTO getRole() {
		return role;
	}

	/**
	 * Sets the name.
	 *
	 * @param name name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the email.
	 *
	 * @param name email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the login.
	 *
	 * @param name login.
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Sets the role.
	 *
	 * @param name role.
	 */
	public void setRole(RoleDTO role) {
		this.role = role;
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
	 * @param id ID.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the new password.
	 * 
	 * @return New password.
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 * 
	 * @param newPassword New password.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Gets the current password.
	 * 
	 * @return Current password.
	 */
	public String getCurrentPassword() {
		return currentPassword;
	}

	/**
	 * Sets the current password.
	 * 
	 * @param currentPassword Current password.
	 */
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
