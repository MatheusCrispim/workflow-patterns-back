package io.demo.jony.jony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.demo.jony.jony.core.service.CrudService;
import io.demo.jony.jony.model.Role;
import io.demo.jony.jony.repository.RoleRepository;

/**
 * CRUD service of the model: Role.
 *
 * @author Virtus
 */
@Service
public class RoleService extends CrudService<Role, Integer> {

	/**
	 * Role repository.
	 */
	@Autowired
	private RoleRepository repository;

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudService#getRepository()
	 */
	@Override
	protected RoleRepository getRepository() {
		return repository;
	}

}