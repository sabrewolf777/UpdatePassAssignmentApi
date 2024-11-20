package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation;

import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordUpdateValidator implements ConstraintValidator<ValidPasswordUpdate, UpdatePasswordAssignmentInstanceRecordRq> {

    @Override
    public void initialize(ValidPasswordUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(UpdatePasswordAssignmentInstanceRecordRq request, ConstraintValidatorContext context) {
    	    	
        if (request == null) {
            return false;
        }
        
        // Validar campos requeridos
      
        if ( (request.getPasswordAssignment() == null || request.getPasswordAssignment().getPasswordValue() == null) ||  
        	 request.getPasswordAssignment().getPasswordValue().trim().isEmpty()) {
            	addConstraintViolation(context, "El passwordValues es requerida");
            return false;
        }
        
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            addConstraintViolation(context, "El username es requerido");
            return false;
        }

        
        if ( (request.getCustomerReference() == null || request.getCustomerReference().getPartyReference() == null || 
          	  request.getCustomerReference().getPartyReference().getPartyType() == null ) ||  
          	  request.getCustomerReference().getPartyReference().getPartyType().trim().isEmpty()) {
              	addConstraintViolation(context, "El dato partyType es requerida");
              return false;
        }
        

        if (request.getReason() == null || request.getReason().trim().isEmpty()) {
            addConstraintViolation(context, "El dato reason es requeridos");
            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
               .addConstraintViolation();
    }
} 