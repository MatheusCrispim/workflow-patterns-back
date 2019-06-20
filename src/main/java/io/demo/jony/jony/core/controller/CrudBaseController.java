package io.demo.jony.jony.core.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.demo.jony.jony.core.dto.ModelDTO;
import io.demo.jony.jony.core.exception.BusinessException;
import io.demo.jony.jony.core.model.Model;
import io.demo.jony.jony.core.security.authorization.DeletePermission;
import io.demo.jony.jony.core.security.authorization.InsertPermission;
import io.demo.jony.jony.core.security.authorization.UpdatePermission;
import io.demo.jony.jony.core.service.CrudService;
import io.demo.jony.jony.core.util.NullAwareBeanUtils;
import io.swagger.annotations.ApiOperation;

/**
 * The 'CrudBaseController' class provides the common API for CRUD controllers.
 * <p>
 * If a controller is a model CRUD, this controller MUST extend this class.
 * <p>
 * Provides insertion, update and deletion for the model.
 *
 * @param <M> Model type.
 * @param <T> Model ID type.
 * @param <D> Model DTO type.
 * @author Virtus
 */
public abstract class CrudBaseController<M extends Model<T>, T extends Serializable, D extends ModelDTO> extends SearchBaseController<M, T, D> {

	/**
	 * Gets the model CRUD service.
	 *
	 * @return Model CRUD service.
	 */
	protected abstract CrudService<M, T> getService();

	/**
	 * Inserts the model instance.
	 *
	 * @param modelDTO Model DTO.
	 * @return Response.
	 * @throws BusinessException 
	 */
	@ApiOperation(value = "Add an item")
	@PostMapping
	@InsertPermission
	public ResponseEntity<D> insert(HttpServletRequest request, @Valid @RequestBody D modelDTO) throws BusinessException {
		M model = getService().insert(toModel(modelDTO));
		return created(toDTO(model));
	}

	/**
	 * Updates the model instance.
	 *
	 * @param id       ID of instance.
	 * @param modelDTO Model DTO.
	 * @return Response.
	 * @throws BusinessException 
	 */
	@ApiOperation(value = "Update an item")
	@PutMapping("/{id}")
	@UpdatePermission
	public ResponseEntity<D> update(HttpServletRequest request, @Valid @PathVariable T id, @RequestBody D modelDTO) throws BusinessException {
		getService().update(id, toModel(modelDTO));
		return ok(modelDTO);
	}

	/**
	 * Updates the model instance partially.
	 *
	 * @param id       ID of instance.
	 * @param modelDTO Model.
	 * @return Response.
	 * @throws Exception
	 */
	@ApiOperation(value = "Update partial of an item")
	@PatchMapping("/{id}")
	@UpdatePermission
	public ResponseEntity<D> updatePartial(HttpServletRequest request, @PathVariable T id, @RequestBody D modelDTO) throws Exception {
		M model = toModel(modelDTO);
		M dbModel = getService().getOne(id);
		NullAwareBeanUtils.getInstance().copyProperties(dbModel, model);
		getService().update(id, dbModel);
		return ok(modelDTO);
	}

	/**
	 * Deletes the model instance.
	 *
	 * @param id ID of instance.
	 * @return Response.
	 * @throws Exception
	 */
	@ApiOperation(value = "Delete an item")
	@DeleteMapping("/{id}")
	@DeletePermission
	public ResponseEntity<?> delete(HttpServletRequest request, @PathVariable T id) throws BusinessException {
		getService().delete(id);

		return success();
	}

	/**
	 * Response CREATED (201) for the REST requests.
	 *
	 * @param element Element.
	 * @return CREATED (201).
	 */
	protected <E> ResponseEntity<E> created(E element) {
		return new ResponseEntity<>(element, HttpStatus.CREATED);
	}
}
