package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.com.dinersclub.issuedDeviceAdministration.application.create.PasswordCreateUseCase;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/passwordAssignmentInstanceRecord")
@Slf4j
@CrossOrigin
public class PasswordController {

	private final PasswordCreateUseCase passwordCreateUseCase;
  

    @PutMapping("/update")
    public ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> passwordUpdate(@RequestBody UpdatePasswordAssignmentInstanceRecordRq request, 
    																			   @RequestHeader HttpHeaders headers) {
    	try {
            log.info("Recibiendo solicitud de actualización de contraseña request REST: {}, headers:{}",request,headers);

    	}catch(Exception e) {
    		 log.info("ERROR: {}",e);
    	}
        return new ResponseEntity<>(passwordCreateUseCase.passwordUpdate(request, headers), HttpStatus.OK);
    }
    
    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        log.info("Recibiendo solicitud de actualización de contraseña request REST: {}, headers:{}");
        return new ResponseEntity<>("Response OK", HttpStatus.OK);
    }
    
} 