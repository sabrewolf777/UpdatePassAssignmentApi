package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.com.dinersclub.issuedDeviceAdministration.application.service.VersionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/version")
@Slf4j
public class VersionController {

    @Autowired
    private VersionService versionService;

    @GetMapping
    public ResponseEntity<String> getVersion() {
        log.info("Obteniendo versión de la aplicación");
        String version = versionService.getVersion();
        return ResponseEntity.ok(version);
    }
} 