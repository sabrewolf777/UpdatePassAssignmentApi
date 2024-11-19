package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinbodyRes {
	private String descripcionTransaccion;
	private String codigoTransaccion;
	private String fechaTransaccion;
	private String horaTransaccion;
}
