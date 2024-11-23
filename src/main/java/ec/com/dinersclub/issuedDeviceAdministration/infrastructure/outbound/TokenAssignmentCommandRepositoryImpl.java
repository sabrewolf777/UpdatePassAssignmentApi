package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.TokenAssignmentCommandRepository;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest.TokenAssignmentRestClientImpl;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenAssignmentCommandRepositoryImpl implements TokenAssignmentCommandRepository {

	private final TokenAssignmentRestClientImpl tokenAssignmentRestClientImpl;
	
	public InitiateTokenAssignmentRs generaOTP(InitiateTokenAssignmentRq request, HttpHeaders headers) {
		return tokenAssignmentRestClientImpl.generaOTP(request, headers);
	}
} 