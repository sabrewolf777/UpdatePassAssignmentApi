package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
