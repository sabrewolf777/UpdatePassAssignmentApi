package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.PasswordCommandRepository;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest.PasswordUpdateRestClientImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PasswordCommandRepositoryImpl implements PasswordCommandRepository {

	//private final PasswordGrpcApiClient passwordGrpcApiClient;
	
	//@Inject
	private final PasswordUpdateRestClientImpl passwordClient;
	
	@Override
	public Optional<UpdatePasswordAssignmentInstanceRecordRs> updatePost(
			UpdatePasswordAssignmentInstanceRecordRq request, HttpHeaders headers) {
			log.info("::::::::: PasswordCommandRepositoryImpl ::::::::::");
		return Optional.ofNullable(passwordClient.passwordUpdate(request, headers));
	}

}
