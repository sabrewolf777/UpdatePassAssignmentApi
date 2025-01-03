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
public class Paginado {
	  private int cantRegistros;
	    private int numTotalPag;
	    private int numPagActual;
}
