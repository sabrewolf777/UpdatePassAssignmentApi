package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.com.dinersclub.issuedDeviceAdministration.application.create.PasswordCreateUseCase;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import lombok.RequiredArgsConstructor;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation.ValidPasswordUpdate;
import jakarta.validation.Valid;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation.ValidHeaders;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/v1/passwordAssignmentInstanceRecord")
public class PasswordController {

	private static final Logger log = LoggerFactory.getLogger(PasswordController.class.getName());

	private final PasswordCreateUseCase passwordCreateUseCase;
  
    @PutMapping("/update")
    public ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> passwordUpdate(
        @Valid @ValidPasswordUpdate @RequestBody UpdatePasswordAssignmentInstanceRecordRq request,
        @Valid @ValidHeaders @RequestHeader HttpHeaders headers) {
        log.info("Recibiendo solicitud de actualización de contraseña request REST: {}, headers:{}",request,headers);
        return new ResponseEntity<>(passwordCreateUseCase.passwordUpdate(request, headers),HttpStatus.OK);
    }

} 