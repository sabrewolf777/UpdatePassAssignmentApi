package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinnersModelResponse {
    private DinHeader dinHeader;
    private DinbodyRes dinBody;
    private DinError dinError;
    private String tipo;
    private String fecha;
    private String origen;
    private String codigo;
    private String codigoErrorProveedor;
    private String mensaje;
    private String detalle;
}