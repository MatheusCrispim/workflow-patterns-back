package io.demo.jony.jony.core.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for versioning the API. <br><br>
 * 
 * Defines the range versions of the controller. <br>
 * Ex: V1, V2 -> All endpoints will be published for all versions 
 * between V1 and V2. <br><br>
 * 
 * If only one version is declared, it is assumed that the last one
 * is the latest.
 * 
 * @author Virtus
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

	/**
	 * Range value of all versions that should be published. 
	 * 
	 * @return API Version range.
	 */
	ApiVersions[] value();
}
