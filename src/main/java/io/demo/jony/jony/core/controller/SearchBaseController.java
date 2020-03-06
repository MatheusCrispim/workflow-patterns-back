package io.demo.jony.jony.core.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.demo.jony.jony.core.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.demo.jony.jony.core.dto.ModelDTO;
import io.demo.jony.jony.core.dto.PageListDTO;
import io.demo.jony.jony.core.dto.SearchFilterDTO;
import io.demo.jony.jony.core.model.Model;
import io.demo.jony.jony.core.security.authorization.ReadPermission;
import io.demo.jony.jony.core.service.SearchService;
import io.demo.jony.jony.core.util.JSonUtil;
import io.swagger.annotations.ApiOperation;
import net.jodah.typetools.TypeResolver;

/**
 * The 'SearchBaseController' class provides the common API for controllers
 * to search models.
 * <p>
 * If a controller needs to search using a model, this controller MUST extend this class.
 * <p>
 * Provides search operations.
 *
 * If a controller needs to search using a model, this controller MUST extend this class.
 *
 * Provides search operations.
 *
 * @author Virtus
 *
 * @param <M> Model type.
 * @param <T> Model ID type.
 * @param <D> Model DTO type.
 * @author Virtus
 */
public abstract class SearchBaseController<M extends Model<T>, T extends Serializable, D extends ModelDTO> extends BaseController {

	/**
	 * Index order of the Model class in generics declaration. 
	 */
	protected static final Integer MODEL_INDEX_ORDER = 0;
	
	/**
	 * Index order of the DTO class in generics declaration. 
	 */
	protected static final Integer DTO_INDEX_ORDER = 2;
	
    /**
     * The model search service.
     *
     * @return Model search service.
     */
    protected abstract SearchService<M, T> getService();

    /**
     * Searchs the model with the filter.
     *
     * @return DTO with list of model founded and filtered.
     */
    @ApiOperation(value = "View a list of available items")
    @GetMapping
    @ReadPermission
    public ResponseEntity<ModelDTO> search(HttpServletRequest request) {
        SearchFilterDTO filter = makeSearchFilter(request);
        
        PageListDTO response = searchInService(request, filter);
        response.setItems(toSearchListDTO(response.getItems()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Searchs the model with the filter.
     *
     * @param filterJSon Filter as JSon text.
     * @return DTO with list of model founded and filtered.
     */
    @ApiOperation(value = "View a list of items filtered by the filter param")
    @GetMapping(params = {"filter"})
    @ReadPermission
	public ResponseEntity<ModelDTO> search(HttpServletRequest request, @RequestParam("filter") String filterJSon) throws BusinessException {
		SearchFilterDTO filter = JSonUtil.fromJSon(filterJSon, SearchFilterDTO.class);
		filter = customSearchFilter(request, filter);
		
		PageListDTO response = searchInService(request, filter);
		response.setItems(toSearchListDTO(response.getItems()));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

    /**
     * Gets one model by its specific ID as DTO.
     *
     * @param id ID of instance.
     * @return DTO of Model instance founded.
     */
    @ApiOperation(value = "Get a item with an ID")
    @GetMapping("/{id}")
    @ReadPermission
    public D getOne(HttpServletRequest request, @PathVariable T id) {
        return toDTO(getOneModel(id));
    }
    
    /**
     * Creates a default search filter.
     * 
     * @param request
     * 		Http Request.
     * @return Search filter.
     */
    protected SearchFilterDTO makeSearchFilter(HttpServletRequest request) {
    	return new SearchFilterDTO();
    }
    
    /**
     * Customize the search filter.
     * 
     * @param request
     * 		Http Request.
     * @param filter
     * 		Search filter.
     * @return Custom search filter. 
     */
    protected SearchFilterDTO customSearchFilter(HttpServletRequest request, SearchFilterDTO filter) {
    	return filter;
    }
    
    /**
     * Call the service to search the items.
     * 
     * @param request
     * 		Http Request.
     * @param filter
     * 		Search Filter.
     * @return Pageable List.
     */
    protected PageListDTO searchInService(HttpServletRequest request, SearchFilterDTO filter) {
    	return getService().search(filter);
    }
    
    /**
     * Gets one model by its specific ID as DTO.
     *
     * @param id ID of instance.
     * @return Model instance founded.
     */
    protected M getOneModel(T id) {
    	return getService().getOne(id);
    }

    /**
     * Converts the Model DTO into Model.
     *
     * @param modelDTO Model DTO.
     * @return Model.
     */
    protected M toModel(D modelDTO) {
        return mapTo(modelDTO, getModelClass());
    }

    /**
     * Converts the Model into Model DTO.
     *
     * @param model Model.
     * @return Model DTO.
     */
    protected D toDTO(M model) {
        return mapTo(model, getDTOClass());
    }

    /**
     * Converts the list of models into list of DTOs.
     *
     * @param items Model items.
     * @return DTOs.
     */
    protected List<D> toListDTO(List<?> items) {
        return toList(items, getDTOClass());
    }
    
    /**
     * Converts the list of models into list of Search DTOs.
     * 
     * @param items
     * 		Model items.
     * @return Search DTOs.
     */
    protected List<? extends ModelDTO> toSearchListDTO(List<?> items) {
        return toList(items, getSearchDTOClass());
    }

    /**
     * Gets the Model class.
     * 
     * @return Model class.
     */
    @SuppressWarnings("unchecked")
	protected Class<M> getModelClass() {
    	return (Class<M>) getTypeArg()[MODEL_INDEX_ORDER];
    }
    
    /**
     * Gets the DTO class.
     * 
     * @return DTO class.
     */
    @SuppressWarnings("unchecked")
	protected Class<D> getDTOClass() {
    	return (Class<D>) getTypeArg()[DTO_INDEX_ORDER];
    }
    
    /**
     * Gets the Search DTO class.
     * 
     * @return Search DTO class.
     */
    protected Class<? extends ModelDTO> getSearchDTOClass() {
        return getDTOClass();
    }
    
    /**
     * Gets the type args of the class.
     * 
     * @return Type args.
     */
    protected Class<?>[] getTypeArg() {
    	return TypeResolver.resolveRawArguments(SearchBaseController.class, getClass());
    }
}
