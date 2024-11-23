package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest;

import org.springframework.http.HttpHeaders;

import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;

public interface TokenAssignmentRestClient {
	 public InitiateTokenAssignmentRs generaOTP(InitiateTokenAssignmentRq request, HttpHeaders headers);
} 