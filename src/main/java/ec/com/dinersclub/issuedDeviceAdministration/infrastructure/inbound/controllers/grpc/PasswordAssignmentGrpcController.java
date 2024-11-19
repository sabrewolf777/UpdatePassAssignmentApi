package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.grpc;

//import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

import com.google.cloud.audit.RequestMetadata;

import ec.com.dinersclub.issuedDeviceAdministration.application.create.PasswordCreateUseCase;
import ec.com.dinersclub.issuedDeviceAdministration.application.service.PasswordService;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.CustomerReference;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.IdentifierValue;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.LogEntry;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PartyReference;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PasswordAssignment;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PasswordAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PaymentCard;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.TokenAssignment;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.TokenIdentificationCode;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UsageLog;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordAssignmentServiceGrpc;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.Rec_updatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.Rec_updatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.ResponsePasswordAssignment;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.StatusInstanceRecord;
import io.grpc.Metadata;
import io.grpc.stub.StreamObserver;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class PasswordAssignmentGrpcController extends PasswordAssignmentServiceGrpc.PasswordAssignmentServiceImplBase {

	private final PasswordCreateUseCase passwordCreateUseCase;
	
	//public PasswordAssignmentGrpcController(){}
	private Metadata requestHeaders;
	
    public void updatePasswordAssignmentInstance(Rec_updatePasswordAssignmentInstanceRecordRq request,
            									 StreamObserver<Rec_updatePasswordAssignmentInstanceRecordRs> responseObserver) {
        	log.info("request grpc.....");
        try {	 
        	 requestHeaders = (Metadata) StoreServer.HEADERS.get();
        	
        	 HttpHeaders headers =getHeaders();
        	 
        	 UpdatePasswordAssignmentInstanceRecordRs  resp=passwordCreateUseCase.passwordUpdate(getReq(request), headers);
        	 
        	 log.info("resp: {}",resp);
        	 
            // Aquí iría la lógica de negocio
            Rec_updatePasswordAssignmentInstanceRecordRs response = Rec_updatePasswordAssignmentInstanceRecordRs.newBuilder()
													            		.setPasswordAssignment(ResponsePasswordAssignment.newBuilder()
													            				.setUsageLog(ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.UsageLog.newBuilder()
													            						.setLogEntry(ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.LogEntry.newBuilder()
													            								.setLogEntryDescription(resp.getPasswordAssignment().getUsageLog().getLogEntry().getLogEntryDescription())
													            								.setLogEntryValueDate(resp.getPasswordAssignment().getUsageLog().getLogEntry().getLogEntryValueDate())
													            								.setLogEntryIdentification(resp.getPasswordAssignment().getUsageLog().getLogEntry().getLogEntryIdentification())
													            								.setLogEntryValueTime(resp.getPasswordAssignment().getUsageLog().getLogEntry().getLogEntryValueTime())
													            								.build())
													            						.build())
													            				.build())	
													            		.setStatusInstanceRecord(StatusInstanceRecord.newBuilder()
														            								.setStatusType(resp.getStatusInstanceRecord().getStatusType())
														            								.setTransactionDate(resp.getStatusInstanceRecord().getTransactionDate())
														            								.setStatus(resp.getStatusInstanceRecord().getStatus())
														            								.setStatusCode(resp.getStatusInstanceRecord().getStatusCode())
														            								.setProviderCode(resp.getStatusInstanceRecord().getProviderCode())
														            								.setMessage(resp.getStatusInstanceRecord().getMessage())
														            								.setDescription(resp.getStatusInstanceRecord().getDescription())
													            								.build()) 
												            		.build();
            log.info("response: {}",response);
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private UpdatePasswordAssignmentInstanceRecordRq getReq(Rec_updatePasswordAssignmentInstanceRecordRq request) {
    	
    	return  UpdatePasswordAssignmentInstanceRecordRq.builder()
    										    .passwordAssignment(PasswordAssignment.builder().passwordStoredValue(request.getPasswordAssignment().getPasswordStoredValue())
    										    										        .passwordValue(request.getPasswordAssignment().getPasswordValue()).build())
    										    .username(request.getUsername())
    										    .customerReference(CustomerReference.builder()
    										    									.partyReference(PartyReference.builder()
    										    																  .partyType(request.getCustomerReference().getPartyReference().getPartyType())
    										    																  .build())			
    										    									.build())
    										    .reason(request.getReason())
    										    .sourceCode(request.getSourceCode())
    										    .paymentCard(PaymentCard.builder().cardNumber(request.getPaymentCard().getCardNumber()).build())
    										    .previousChange(request.getPreviousChange())
    										    .tokenAssignment(TokenAssignment.builder().tokenIdentificationCode(TokenIdentificationCode.builder()
    										    																						  .identifierValue(IdentifierValue.builder()
    										    																								  						  .value("")
    										    																								  						  .build())
    										    																						  .build())
    										    										  .build())
    										    
    										    .build();
    										    
    										    
							
    	
    	
    	
    }
    
    private HttpHeaders getHeaders() {	
    	HttpHeaders headers= new HttpHeaders();
    	headers.add("applicationId", getHeader("applicationId"));
    	headers.add("sesionId", getHeader("sesionId"));
    	headers.add("channelId", getHeader("channelId"));
    	headers.add("device", getHeader("device"));
    	headers.add("portalId", getHeader("portalId"));
    	headers.add("uuid", getHeader("uuid"));
    	headers.add("ipaddress", getHeader("ipaddress"));
    	headers.add("transactionDate", getHeader("transactionDate"));
    	headers.add("simetricKey", getHeader("simetricKey"));
    	headers.add("userId", getHeader("userId"));
    	headers.add("recordsAmount", getHeader("recordsAmount"));
    	headers.add("pagesAmount", getHeader("pagesAmount"));
    	headers.add("pagesCurrentIndex", getHeader("pagesCurrentIndex"));
    	headers.add("pagsKeyValue", getHeader("pagsKeyValue"));   	
    	return headers;
    }
    
    private String getHeader(String key) {
        Metadata.Key<String> metadataKey = Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER);
        return requestHeaders.get(metadataKey);
    }
    
} 