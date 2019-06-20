package io.demo.jony.jony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.jony.jony.core.api.ApiVersion;
import io.demo.jony.jony.core.api.ApiVersions;
import io.demo.jony.jony.core.controller.CrudBaseController;
import io.demo.jony.jony.core.security.authorization.Authorization;
import io.demo.jony.jony.dto.PermissionDTO;
import io.demo.jony.jony.model.Permission;
import io.demo.jony.jony.service.PermissionService;
import io.swagger.annotations.Api;

/**
 * CRUD Controller for the model: Permission.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("permissions")
@Authorization("permission")
@Api(value = "permission", tags = "permission-controller")
@ApiVersion(ApiVersions.V1)
public class PermissionController extends CrudBaseController<Permission, Integer, PermissionDTO> {

	/**
	 * Permission service.
	 */
	@Autowired
	private PermissionService service;

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudBaseController#getService()
	 */
	@Override
	protected PermissionService getService() {
		return service;
	}

}
