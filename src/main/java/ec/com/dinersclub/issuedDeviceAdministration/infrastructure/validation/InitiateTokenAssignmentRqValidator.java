package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation;


import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InitiateTokenAssignmentRqValidator implements ConstraintValidator<ValidInitiateTokenAssignmentRq, InitiateTokenAssignmentRq> {

	@Override
	public boolean isValid(InitiateTokenAssignmentRq  request, ConstraintValidatorContext context) {
	    if (request == null) {
            return false;
        }
        
	    if ( (request.getCustomerReference() == null || request.getCustomerReference().getPartyReference() == null || 
	          	  request.getCustomerReference().getPartyReference().getPartyType() == null ) ||  
	          	  request.getCustomerReference().getPartyReference().getPartyType().trim().isEmpty()) {
	              	addConstraintViolation(context, "El dato partyType es requerida");
	              return false;
	    }
	    
        if (request.getTransactionCode() == null || request.getTransactionCode().trim().isEmpty()) {
            addConstraintViolation(context, "El dato TransactionCode es requerido");
            return false;
        }
        

        if ( (request.getOrganisation() == null || request.getOrganisation().getOrganisationIdentification() == null || 
        		request.getOrganisation().getOrganisationIdentification().get(0).getIdentifier() == null  ||  
        		request.getOrganisation().getOrganisationIdentification().get(0).getIdentifier().getIdentifierValue() == null ) || 
        		request.getOrganisation().getOrganisationIdentification().get(0).getIdentifier().getIdentifierValue().trim().isEmpty()) {
                addConstraintViolation(context, "El dato IdentifierValue es requerido");
                return false;
          }
        
        if ( (request.getTokenAssignment() == null || request.getTokenAssignment().getTokenIdentificationCode() == null || 
        		request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue() == null  ||  
        	    request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue() == null ) || 
        		request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue().trim().isEmpty()) {
                addConstraintViolation(context, "El dato IdentifierValue es requerido");
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
