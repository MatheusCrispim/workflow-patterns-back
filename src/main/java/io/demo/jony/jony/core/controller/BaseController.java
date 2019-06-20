package io.demo.jony.jony.core.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import io.demo.jony.jony.core.dto.RestMessageDTO;
import io.demo.jony.jony.core.util.MapperUtil;
import io.demo.jony.jony.core.util.MessageUtil;

/**
 * The 'BaseController' class provides the common API for all controllers.
 * 
 * All controllers MUST extend this class.
 * 
 * @author Virtus
 */
public abstract class BaseController {

	/**
	 * Logger.
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Returns a success response.
	 * 
	 * @return Success response.
	 */
	protected ResponseEntity<?> success() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
     * Response for the REST requests.
     *
     * @param element    Element.
     * @param status Http Status.
     * @return Response.
     */
    protected <E> ResponseEntity<E> responseEntity(E element, HttpStatus status) {
        return new ResponseEntity<>(element, status);
    }

    /**
     * Response OK (200) for the REST requests.
     *
     * @param element Element.
     * @return OK (200).
     */
    protected <E> ResponseEntity<E> ok(E element) {
    	return responseEntity(element, HttpStatus.OK);
    }
    
    /**
     * Response Not Acceptable(406) for the REST requests.
     * @param e Throw Exception e.
     * @return NOT ACCEPTABLE (406).
     */
	protected <E> ResponseEntity<E> notAcceptable(Exception e) {
        return notAcceptable(e.getMessage());
    }

	/**
	 * Response Not Acceptable(406) for the REST requests.
	 * 
	 * @param message Message
	 * @return NOT ACCEPTABLE (406).
	 */
    @SuppressWarnings("unchecked")
	protected <E> ResponseEntity<E> notAcceptable(String message) {
    	return new ResponseEntity<>((E) new RestMessageDTO(MessageUtil.findMessage(message, LocaleContextHolder.getLocale())), HttpStatus.NOT_ACCEPTABLE);
    }
    
    /**
     * Response Conflict(409) for the REST requests.
     * @param e Throw Exception e.
     * @return CONFLICT (409).
     */
	protected <E> ResponseEntity<E> conflitct(Exception e) {
        return conflitct(e.getMessage());
    }
	
	/**
	 * Response Conflict(409) for the REST requests.
	 * @param message Exception message.
	 * @return CONFLICT (409).
	 */
	@SuppressWarnings("unchecked")
	protected <E> ResponseEntity<E> conflitct(String message) {
		return new ResponseEntity<>((E) new RestMessageDTO(MessageUtil.findMessage(message, LocaleContextHolder.getLocale())), HttpStatus.CONFLICT);
	}
    
	/**
	 * Maps the source object the a new destination type instance.
	 * 
	 * @param source
	 * 		Source object.
	 * @param destClass
	 * 		Destination class.
	 * @return Instance of destination type.
	 */
    protected <S, D> D mapTo(S source, Class<D> destClass) {
        return MapperUtil.mapTo(source, destClass);
    }

    /**
     * Converts the source list to a destination list mapping the source items
	 * to destination type items.
     * 
     * @param items
     * 		Source items.
     * @param destClass
     * 		Destination class.
     * @return List of instances of destination type.
     */
    protected <D, S> List<D> toList(List<S> items, Class<D> destClass) {
        return MapperUtil.toList(items, destClass);
    }
    
    /**
     * Extracts the path variable in runtime execution.
     * 
     * @param request
     * 		Http Request.
     * @param variable
     * 		Variable name.
     * @return Variable value.
     */
    protected String getPathVariable(HttpServletRequest request, String variable) {
    	ResourceUrlProvider urlProvider = (ResourceUrlProvider) request.getAttribute(ResourceUrlProvider.class.getCanonicalName());
    	
    	Map<String, String> params = urlProvider.getPathMatcher().extractUriTemplateVariables(
    	        String.valueOf(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)),
    	        String.valueOf(request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)));

    	return params.get(variable);
    }
}
