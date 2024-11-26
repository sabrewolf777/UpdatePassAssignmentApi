package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = InitiateTokenAssignmentRqValidator.class)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidInitiateTokenAssignmentRq {
	 	String message() default "Datos para generacion de token otp";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}
