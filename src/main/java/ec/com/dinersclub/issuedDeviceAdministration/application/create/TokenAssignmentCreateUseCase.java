package ec.com.dinersclub.issuedDeviceAdministration.application.create;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.TokenAssignmentCommandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenAssignmentCreateUseCase {

	TokenAssignmentCommandRepository tokenAssignmentCommandRepository;
	
	public InitiateTokenAssignmentRs generaOTP(InitiateTokenAssignmentRq request, HttpHeaders headers) {
		 log.info(" *** TACreateUseCase request ***");
		
		return new InitiateTokenAssignmentRs();
	}
} 