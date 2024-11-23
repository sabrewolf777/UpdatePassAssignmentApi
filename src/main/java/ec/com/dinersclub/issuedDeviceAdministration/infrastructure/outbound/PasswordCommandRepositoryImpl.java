package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.repository.PasswordCommandRepository;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest.PasswordUpdateRestClientImpl;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PasswordCommandRepositoryImpl implements PasswordCommandRepository {

	//private final PasswordGrpcApiClient passwordGrpcApiClient;
	
	//@Inject
	private final PasswordUpdateRestClientImpl passwordClient;
	
	@Override
	public ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> updatePost(UpdatePasswordAssignmentInstanceRecordRq request, HttpHeaders headers) {	
		return passwordClient.passwordUpdate(request, headers);
	}

}
