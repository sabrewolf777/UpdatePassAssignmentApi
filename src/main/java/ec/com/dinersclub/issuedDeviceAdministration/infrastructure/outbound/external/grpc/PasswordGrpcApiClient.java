package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.grpc;

import org.springframework.http.HttpHeaders;

import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;

public interface PasswordGrpcApiClient {
	public UpdatePasswordAssignmentInstanceRecordRs passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers);
}
