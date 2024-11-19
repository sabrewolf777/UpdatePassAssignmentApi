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
public class UpdatePasswordAssignmentInstanceRecordRq {
    private PasswordAssignment passwordAssignment;
    private String username;
    private CustomerReference customerReference;
    private String reason;
    private String sourceCode;
    private PaymentCard paymentCard;
    private boolean previousChange;
    private TokenAssignment tokenAssignment;
}
