package io.demo.jony.jony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.demo.jony.jony.core.api.ApiVersion;
import io.demo.jony.jony.core.api.ApiVersions;
import io.demo.jony.jony.core.controller.CrudBaseController;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.security.authorization.Authorization;
import io.demo.jony.jony.dto.UserDTO;
import io.demo.jony.jony.model.User;
import io.demo.jony.jony.service.UserService;
import io.swagger.annotations.Api;

/**
 * CRUD Controller for the model: User.
 *
 * @author Virtus
 */
@RestController
@RequestMapping("users")
@Authorization("user")
@Api(value = "user", tags = "user-controller")
@ApiVersion(ApiVersions.V1)
public class UserController extends CrudBaseController<User, Integer, UserDTO> {

	/**
	 * User service.
	 */
	@Autowired
	private UserService service;

	/**
	 * (non-Javadoc)
	 *
	 * @see CrudBaseController#getService()
	 */
	@Override
	protected UserService getService() {
		return service;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see io.demo.jony.jony.core.controller.SearchBaseController#toModel(io.demo.jony.jony.core.dto.ModelDTO)
	 */
	@Override
	protected User toModel(UserDTO modelDTO) {
		User model = super.toModel(modelDTO);
		model.setPassword(modelDTO.getNewPassword());
		return model;
	}

	/**
	 * Updates the password of the current User.
	 * 
	 * @param dto DTO.
	 * @return If the password was updated.
	 * @throws BusinessException
	 */
	@PatchMapping("/current/password")
	public ResponseEntity<?> updateCurrentPassword(@RequestBody UserDTO dto) throws BusinessException {
		getService().updateCurrentPassword(dto.getCurrentPassword(), dto.getNewPassword());

		return success();
	}
}