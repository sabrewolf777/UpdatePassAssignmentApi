package ec.com.dinersclub.issuedDeviceAdministration.application.create;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.PasswordCommandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordCreateUseCase {

	 private final PasswordCommandRepository passwordCommandRepository;
	
	 public UpdatePasswordAssignmentInstanceRecordRs passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {		 		 
		 return passwordCommandRepository.passwordUpdate(request, headers);
	 }
	
}
