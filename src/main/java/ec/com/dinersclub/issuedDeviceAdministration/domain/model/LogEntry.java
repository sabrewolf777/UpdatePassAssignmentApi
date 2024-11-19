package ec.com.dinersclub.issuedDeviceAdministration.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogEntry {
	private String logEntryDescription;
	private String logEntryIdentification;
	private String logEntryValueDate;
	private String logEntryValueTime;
}
