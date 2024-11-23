package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitiateTokenAssignmentRs {

	 	@Getter
	    @Setter
	    @NoArgsConstructor
	    @AllArgsConstructor
	    @Builder
	    public static class TokenAssignment {
	        private String description;
	    }
	 
	 	private StatusInstanceRecord instanceRecord;
	 	
}
