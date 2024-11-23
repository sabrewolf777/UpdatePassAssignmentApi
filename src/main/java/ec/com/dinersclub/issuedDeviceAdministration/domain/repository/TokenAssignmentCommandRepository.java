package ec.com.dinersclub.issuedDeviceAdministration.domain.repository;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;

public interface TokenAssignmentCommandRepository {
	public InitiateTokenAssignmentRs generaOTP(InitiateTokenAssignmentRq request, HttpHeaders headers);
} 