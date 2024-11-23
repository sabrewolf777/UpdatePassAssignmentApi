package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.TokenAssignmentCommandRepository;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest.TokenAssignmentRestClientImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TokenAssignmentCommandRepositoryImpl implements TokenAssignmentCommandRepository {

	private final TokenAssignmentRestClientImpl tokenAssignmentRestClientImpl;
	
	@Override
	public Optional<InitiateTokenAssignmentRs> generaOTP(InitiateTokenAssignmentRq request, HttpHeaders headers) {
		log.info("::::::::: PasswordCommandRepositoryImpl ::::::::::");
		return Optional.empty();
	}
} 