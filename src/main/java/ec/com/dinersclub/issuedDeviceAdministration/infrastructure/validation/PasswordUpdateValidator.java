package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation;

import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import io.quarkus.logging.Log;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordUpdateValidator implements ConstraintValidator<ValidPasswordUpdate, UpdatePasswordAssignmentInstanceRecordRq> {

    @Override
    public void initialize(ValidPasswordUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(UpdatePasswordAssignmentInstanceRecordRq request, ConstraintValidatorContext context) {
    	
    	log.info("request: {}",request);
    	
    	/*
        if (request == null) {
            return false;
        }
        
        
        // Validar campos requeridos
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            addConstraintViolation(context, "El username es requerido");
            return false;
        }

        if (request.getCustomerReference() == null) {
            addConstraintViolation(context, "La referencia del cliente es requerida");
            return false;
        }

        if (request.getPaymentCard() == null) {
            addConstraintViolation(context, "Los datos de la tarjeta son requeridos");
            return false;
        }
*/
        // Agregar más validaciones específicas según necesidades

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
} 