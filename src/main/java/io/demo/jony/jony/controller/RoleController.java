package io.demo.jony.jony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.jony.jony.core.api.ApiVersion;
import io.demo.jony.jony.core.api.ApiVersions;
import io.demo.jony.jony.core.controller.CrudBaseController;
import io.demo.jony.jony.core.security.authorization.Authorization;
import io.demo.jony.jony.dto.RoleDTO;
import io.demo.jony.jony.model.Role;
import io.demo.jony.jony.service.RoleService;
import io.swagger.annotations.Api;

/**
 * CRUD Controller for the model: Role.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("roles")
@Authorization("role")
@Api(value = "role", tags = "role-controller")
@ApiVersion(ApiVersions.V1)
public class RoleController extends CrudBaseController<Role, Integer, RoleDTO> {

	/**
	 * Role service.
	 */
	@Autowired
	private RoleService service;

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudBaseController#getService()
	 */
	@Override
	protected RoleService getService() {
		return service;
	}

}
