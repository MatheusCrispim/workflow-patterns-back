package io.demo.jony.jony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.service.CrudService;
import io.demo.jony.jony.core.util.CryptoUtil;
import io.demo.jony.jony.model.User;
import io.demo.jony.jony.repository.UserRepository;

/**
 * Crud Service for model: User.
 *
 * @author Virtus
 */
@Service
public class UserService extends CrudService<User, Integer> {

	/**
	 * User repository.
	 */
	@Autowired
	private UserRepository repository;

	/**
	 * (non-Javadoc)
	 *
	 * @see io.demo.jony.jony.core.service.CrudService#getRepository()
	 */
	@Override
	protected UserRepository getRepository() {
		return repository;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see io.demo.jony.jony.core.service.CrudService#insert(io.demo.jony.jony.core.model.Model)
	 */
	@Override
	public User insert(User model) throws BusinessException {
		model.setPassword(CryptoUtil.hash(model.getPassword()));

		return super.insert(model);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see io.demo.jony.jony.core.service.CrudService#validateInsert(io.demo.jony.jony.core.model.Model)
	 */
	@Override
	protected void validateInsert(User model) throws BusinessException {
		/* If Login already exists */
		if (getRepository().existsByLogin(model.getLogin())) {
			throw new BusinessException("user.login.exists");
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see io.demo.jony.jony.core.service.CrudService#validateUpdate(io.demo.jony.jony.core.model.Model)
	 */
	@Override
	protected void validateUpdate(User model) throws BusinessException {
		/* If login already exists and it is NOT me */
		if (getRepository().existsByLoginAndIdNot(model.getLogin(), model.getId())) {
			throw new BusinessException("user.login.exists");
		}
	}

	/**
	 * Login the User.
	 *
	 * @param login    Login.
	 * @param password Password.
	 * @return User.
	 */
	public User login(String login, String password) {
		return getRepository().login(login, CryptoUtil.hash(password));
	}

	/**
	 * Find the User by login.
	 *
	 * @param login Login.
	 * @return User.
	 */
	public User findByLogin(String login) {
		return getRepository().findByLogin(login);
	}

	/**
	 * Updates the password of the current User.
	 * 
	 * @param currentLogin Current login.
	 * @param newLogin     New login.
	 * @throws BusinessException
	 */
	@Transactional
	public void updateCurrentPassword(String currentPassword, String newPassword) throws BusinessException {
		this.updatePassword(getCurrentUserId(), currentPassword, newPassword);
	}

	/**
	 * Updates the password of the specified User.
	 * 
	 * @param id              User ID.
	 * @param currentPassword Current password.
	 * @param newPassword     New password.
	 * @throws BusinessException
	 */
	@Transactional
	public void updatePassword(Integer id, String currentPassword, String newPassword) throws BusinessException {
		if (getRepository().isPasswordCorrect(id, CryptoUtil.hash(currentPassword)) == null) {
			throw new BusinessException("user.password.wrong");
		}

		getRepository().updatePassword(id, CryptoUtil.hash(newPassword));
	}
}
