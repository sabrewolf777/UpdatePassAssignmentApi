package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinBody {
	 	private String clave;
	    private String nuevaClave;
	    private String usuarioBiometrico;
	    private String perfil;
	    private String tipoIngreso;
	    private String codigoUnicoAplicacion;
	    private String ultimosDigitosTarjeta;
	    private boolean cambioPrevio;
	    private String token;
}
