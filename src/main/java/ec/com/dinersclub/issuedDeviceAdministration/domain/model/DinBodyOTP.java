package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinBodyOTP {
	private String identificacionUsuario;
	private String perfil;
	private String usuarioBiometrico;
	private String transaccion;
	private String rucEmpresa;
	private String token;
}
