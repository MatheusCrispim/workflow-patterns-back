package io.demo.jony.jony.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import io.demo.jony.jony.core.api.ApiVersionRequestMapping;

/**
 * Custom configuration support for WEB MVC. 
 * 
 * @author Virtus
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	/**
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#createRequestMappingHandlerMapping()
	 */
	@Override
	protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
		return new ApiVersionRequestMapping();
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
