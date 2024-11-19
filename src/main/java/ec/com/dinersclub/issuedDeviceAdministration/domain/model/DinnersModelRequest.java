package ec.com.dinersclub.issuedDeviceAdministration.domain.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinnersModelRequest {
    private DinHeader dinHeader;
    private DinBody dinBody;
}