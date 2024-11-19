package ec.com.dinersclub.issuedDeviceAdministration.domain.repository;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;

public interface PasswordCommandRepository {
    Optional<UpdatePasswordAssignmentInstanceRecordRs> updatePost(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers);
}
