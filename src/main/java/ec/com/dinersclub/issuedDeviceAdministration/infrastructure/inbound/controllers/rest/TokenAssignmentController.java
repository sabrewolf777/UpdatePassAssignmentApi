package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.com.dinersclub.issuedDeviceAdministration.application.create.TokenAssignmentCreateUseCase;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/v1/tokenAssignment")
public class TokenAssignmentController {
	
    private static final Logger log = LoggerFactory.getLogger(TokenAssignmentController.class.getName());

	private final TokenAssignmentCreateUseCase tokenAssignmentCreateUseCase;
	
    @PostMapping("/initiate")
    public ResponseEntity<InitiateTokenAssignmentRs> generateOTP(InitiateTokenAssignmentRq request, HttpHeaders headers) {
        log.info("Recibiendo solicitud para generar OTP request REST: {}, headers:{}",request,headers);
        return new ResponseEntity<>(tokenAssignmentCreateUseCase.generaOTP(request, headers), HttpStatus.OK);
    }
} 