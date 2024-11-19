package ec.com.dinersclub.issuedDeviceAdministration.application.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VersionServiceImpl implements VersionService {

    @Override
    public String getVersion() {
        log.info("Ejecutando obtención de versión");
        return "1.0.0"; // Puedes modificar esto según tus necesidades
    }
} 