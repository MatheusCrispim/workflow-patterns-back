package io.demo.jony.jony.core.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Helper annotation to check permission for: Insert. 
 * 
 * @author Virtus
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@securityService.allow(this, 'insert')")
public @interface InsertPermission {
	
}
