package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordUpdateValidator.class)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPasswordUpdate {
    String message() default "Datos de actualización de contraseña inválidos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 