package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinError {
	    private String tipo;
	    private String fecha;
	    private String origen;
	    private String codigo;
	    private String codigoErrorProveedor;
	    private String mensaje;
	    private String detalle;
}
