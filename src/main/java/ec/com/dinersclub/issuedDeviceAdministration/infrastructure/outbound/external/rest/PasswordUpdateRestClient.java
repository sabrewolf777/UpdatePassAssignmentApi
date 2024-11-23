package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;

public interface PasswordUpdateRestClient {
	ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers);
} 