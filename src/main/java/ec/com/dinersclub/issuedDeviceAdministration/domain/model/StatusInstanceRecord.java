package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusInstanceRecord {
	private String statusType;
	private String transactionDate;
	private String status;
	private String statusCode;
	private String providerCode;
	private String message;
	private String description;
}
