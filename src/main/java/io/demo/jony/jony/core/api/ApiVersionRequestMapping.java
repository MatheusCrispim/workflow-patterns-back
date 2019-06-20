package io.demo.jony.jony.core.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Handler for request mapping the API Version. <br>
 * Uses the @ApiVersion annotation to specified the endpoint.
 * 
 * @author Virtus
 *
 */
public class ApiVersionRequestMapping extends RequestMappingHandlerMapping {

	/**
	 * Pattern for versioning the API.
	 */
	private static final String VERSION_PATTERN = "v%s";
	
	/**
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#getMappingForMethod(java.lang.reflect.Method, java.lang.Class)
	 */
	@Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
    	RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
    	
    	if(info != null) {
    		ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        	if(typeAnnotation != null) {
        		RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        		
        		info = createApiVersionInfo(typeAnnotation, requestMapping, info.getCustomCondition()).combine(info);
        	}
    	}
    	
    	return info;
    }
    
	/**
	 * Creates the request info for API Version.
	 * 
	 * @param versionAnnotation
	 * 		Version Annotation.
	 * @param requestMapping
	 * 		Request Mapping.
	 * @param customCondition
	 * 		Custom Condition.
	 * @return Request info for API Version.
	 */
    protected RequestMappingInfo createApiVersionInfo(ApiVersion versionAnnotation, RequestMapping requestMapping, RequestCondition<?> customCondition) {
    	List<String> paths = new ArrayList<>();
		
		for(ApiVersions version : ApiVersions.between(versionAnnotation.value())) {
			paths.add(String.format(VERSION_PATTERN, version.getVersion()));
		}
    	
		return RequestMappingInfo
			.paths(paths.toArray(new String[0]))
			.methods(requestMapping.method())
			.params(requestMapping.params())
			.headers(requestMapping.headers())
			.consumes(requestMapping.consumes())
			.produces(requestMapping.produces())
			.mappingName(requestMapping.name())
			.customCondition(customCondition)
			.build();
    }
}
