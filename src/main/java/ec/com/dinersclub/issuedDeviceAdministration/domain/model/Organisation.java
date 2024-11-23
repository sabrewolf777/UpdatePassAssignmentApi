package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organisation {
	 private List<OrganisationIdentification> organisationIdentification;
}