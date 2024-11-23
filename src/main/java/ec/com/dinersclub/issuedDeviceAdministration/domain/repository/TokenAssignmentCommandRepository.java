package ec.com.dinersclub.issuedDeviceAdministration.domain.repository;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;

public interface TokenAssignmentCommandRepository {
	public Optional<InitiateTokenAssignmentRs> generaOTP(InitiateTokenAssignmentRq request, HttpHeaders headers);
} 