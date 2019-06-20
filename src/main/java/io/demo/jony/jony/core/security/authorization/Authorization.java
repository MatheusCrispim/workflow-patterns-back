package io.demo.jony.jony.core.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines the suffix for authorization in controller's operations.
 * 
 * @author Virtus
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

	/**
	 * Value of the suffix. 
	 * 
	 * @return Suffix.
	 */
	String value();
}
