package ec.com.dinersclub.issuedDeviceAdministration.application.create;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.PasswordCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordCreateUseCase {

	private final PasswordCommandRepository passwordCommandRepository;
	
	 public UpdatePasswordAssignmentInstanceRecordRs passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
		 
		 log.info("service request:{}",request,headers);
		 
		 Optional<UpdatePasswordAssignmentInstanceRecordRs> response =passwordCommandRepository.updatePost(request, headers);	
		 
		 log.info("service response:{}",response);
		 
		 return response.get();
	 }
	
}
