package io.demo.jony.jony.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import io.demo.jony.jony.core.repository.CrudBaseRepository;
import io.demo.jony.jony.model.User;

/**
 * CRUD Repository for entity: User.
 *
 * @author Virtus
 */
public interface UserRepository extends CrudBaseRepository<User, Integer> {

	/**
	 * Login the user.
	 *
	 * @param login             Login.
	 * @param encryptedPassword Encrypted password.
	 * @return User's data.
	 */
	@Query(value = "select * from user_account where upper(login) = upper(?1) and password = ?2 and deleted = false", nativeQuery = true)
	User login(String login, String encryptedPassword);

	/**
	 * Checks if exists an User with the same login.
	 * 
	 * @param login Login.
	 * @return If exists an user with the same login.
	 */
	boolean existsByLogin(String login);

	/**
	 * Checks if exists an user with the same login and it is not the same as the ID
	 * specified.
	 * 
	 * @param login Login.
	 * @param id    ID.
	 * @return If exists an User with these filters.
	 */
	boolean existsByLoginAndIdNot(String login, Integer id);

	/**
	 * Finds the user by login.
	 * 
	 * @param login Login.
	 * @return User.
	 */
	User findByLogin(String login);

	/**
	 * Checks if the password of the User is correct.
	 * 
	 * @param id       User ID.
	 * @param password password.
	 * @return If the password of the User is correct.
	 */
	@Query(value = "select 1 from user_account where id = ?1 and password = ?2 and deleted = false", nativeQuery = true)
	Integer isPasswordCorrect(Integer id, String password);

	/**
	 * Updates the password of the specified User.
	 * 
	 * @param id          User ID.
	 * @param newPassword New password.
	 */
	@Modifying
	@Query(value = "update user_account set password = ?2 where id = ?1 and deleted = false", nativeQuery = true)
	void updatePassword(Integer id, String newPassword);
}
