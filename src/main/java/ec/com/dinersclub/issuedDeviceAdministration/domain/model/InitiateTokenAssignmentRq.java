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
public class InitiateTokenAssignmentRq {
	private CustomerReference customerReference;
	private String username;
	private String transactionCode;
	private Organisation organisation;
	private TokenAssignment tokenAssignment;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class CustomerReference {
	    private PartyReference partyReference;
	}
	
	 @Getter
	 @Setter
	 @NoArgsConstructor
	 @AllArgsConstructor
	 @Builder
	 public static class PartyReference {
	     private String referenceId;
	     private String partyType;
	 }
	
}
