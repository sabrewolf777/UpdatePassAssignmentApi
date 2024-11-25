package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.mapper.rest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelResponse;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;

@Mapper(componentModel = "spring")
public interface DinnersToResponse {

	@Mappings({
      /*  @Mapping(source = "id", target = "id"),
        @Mapping(source = "code", target = "code"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "description", target = "description"),
        @Mapping(source = "price", target = "price"),
        @Mapping(source = "dateCreated", target = "dateCreated"),
        @Mapping(source = "dateUpdated", target = "dateUpdated")
        */
        }
	)
	
	UpdatePasswordAssignmentInstanceRecordRs toUpair(DinnersModelResponse response);
}
