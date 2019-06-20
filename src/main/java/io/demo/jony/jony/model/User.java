package io.demo.jony.jony.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import io.demo.jony.jony.core.model.Model;

/**
 * Model for table: USER.
 *
 * @author Virtus
 */
@Entity(name = "user_account")
@SQLDelete(sql = "UPDATE user_account SET deleted = true WHERE id = ?")
@Where(clause = Model.WHERE_DELETED_CLAUSE)
public class User extends Model<Integer> {

	/**
	 * User ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/**
	 * Name.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Email.
	 */
	@Column(name = "email")
	private String email;

	/**
	 * Login.
	 */
	@Column(name = "login")
	private String login;

	/**
	 * Password.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * Role.
	 */
	@ManyToOne
	private Role role;

	/**
	 * If it is deleted.
	 */
	@Column(name = "deleted")
	private boolean deleted = false;

	public User() {
	}

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
	 * Gets the password.
	 *
	 * @return password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the role.
	 *
	 * @return role.
	 */
	public Role getRole() {
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
	 * Sets the password.
	 *
	 * @param name password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the role.
	 *
	 * @param name role.
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Gets if it is deleted.
	 * 
	 * @return If it is deleted.
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * Sets if it is deleted.
	 * 
	 * @param deleted If it is deleted.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see Model#getId()
	 */
	@Override
	public Integer getId() {
		return this.id;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see Model#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}