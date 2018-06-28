package br.com.danielseabra.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.danielseabra.constraint.validator.MultipartFileValidator;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartFileConstraint {

	String message() default "{bean.validation.multipartfileconstraint.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
