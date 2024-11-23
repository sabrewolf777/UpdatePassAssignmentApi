package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MsdSegOtpRequest {
	private DinHeader dinHeader;
	private DinBodyOTP dinBody;
}
