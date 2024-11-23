package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.com.dinersclub.issuedDeviceAdministration.application.create.PasswordCreateUseCase;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation.ValidPasswordUpdate;
import jakarta.validation.Valid;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.validation.ValidHeaders;

@RequiredArgsConstructor
@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/v1/passwordAssignmentInstanceRecord")
public class PasswordController {

	private final PasswordCreateUseCase passwordCreateUseCase;
  
    @PutMapping("/update")
    public ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> passwordUpdate(
        @Valid @ValidPasswordUpdate @RequestBody UpdatePasswordAssignmentInstanceRecordRq request,
        @Valid @ValidHeaders @RequestHeader HttpHeaders headers) {
        log.info("Recibiendo solicitud de actualización de contraseña request REST: {}, headers:{}",request,headers);
        return passwordCreateUseCase.passwordUpdate(request, headers);
    }

} 