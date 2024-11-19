package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordAssignment {
	 private String passwordStoredValue;
	 private String passwordValue;
}
