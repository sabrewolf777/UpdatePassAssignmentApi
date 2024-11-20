
package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpHeaders;

public class HeadersValidator implements ConstraintValidator<ValidHeaders, HttpHeaders> {

    @Override
    public void initialize(ValidHeaders constraintAnnotation) {
    }

    @Override
    public boolean isValid(HttpHeaders headers, ConstraintValidatorContext context) {
        
    	/*
    	if (headers == null) {
            addConstraintViolation(context, "Los headers son requeridos");
            return false;
        }

        // Validar headers requeridos
        if (!headers.containsKey("Authorization")) {
            addConstraintViolation(context, "El header Authorization es requerido");
            return false;
        }

        // Validar formato del token Bearer
        String authHeader = headers.getFirst("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            addConstraintViolation(context, "El formato del token de autorización es inválido");
            return false;
        }
		*/
        // Agregar más validaciones de headers según necesidades

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
} 