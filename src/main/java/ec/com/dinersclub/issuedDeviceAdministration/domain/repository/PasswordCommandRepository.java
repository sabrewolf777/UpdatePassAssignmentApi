package ec.com.dinersclub.issuedDeviceAdministration.domain.repository;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;

public interface PasswordCommandRepository {
	ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> updatePost(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers);
}
