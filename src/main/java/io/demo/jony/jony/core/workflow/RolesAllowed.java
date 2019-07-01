package io.demo.jony.jony.core.workflow;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface RolesAllowed {

	String[] value();

}
