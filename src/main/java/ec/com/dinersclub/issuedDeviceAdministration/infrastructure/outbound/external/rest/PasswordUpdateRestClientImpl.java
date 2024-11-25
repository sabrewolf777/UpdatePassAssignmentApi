package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinBody;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinHeader;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelRequest;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelResponse;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.LogEntry;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Paginado;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PasswordAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.StatusInstanceRecord;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Tag;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UsageLog;

@Component
public class PasswordUpdateRestClientImpl implements PasswordUpdateRestClient {

    private static final Logger log = LoggerFactory.getLogger(PasswordUpdateRestClientImpl.class.getName());
	
	@Value("${password.update.api.url}")
	private String apiUrl;
	    
    public UpdatePasswordAssignmentInstanceRecordRs passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
	    
    	final RestTemplate restTemplate= new RestTemplate();
	    	
	    final HttpHeaders hdrs = new HttpHeaders();
	
	    final DinnersModelRequest dinnerRequest=getDinnerRequest(request,headers);
	    	 
	    final HttpEntity<DinnersModelRequest> entity = new HttpEntity<>(dinnerRequest, hdrs);
	
	    final ResponseEntity<DinnersModelResponse> response = restTemplate.exchange(apiUrl,HttpMethod.POST,entity, DinnersModelResponse.class);
	         	         
	    log.info("response: {}",response);
	   
        return getUpair(response);
    }
    
    
    public DinnersModelRequest getDinnerRequest(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
    	DinnersModelRequest resp= null;	
   		final List<Tag> tags = new ArrayList<>();
       	tags.add(Tag.builder().clave(getHeaderValueAsString(headers,"tagsKeyValue"))
				        		  .valor(getHeaderValueAsString(headers,"tagsKeyValue"))
				        		  .build());
    						resp= DinnersModelRequest.builder().dinHeader(DinHeader.builder()
																	.aplicacionId(getHeaderValueAsString(headers,"applicationId")) 
																	.canalId( getHeaderValueAsString(headers,"channelId"))
																	.sesionId(getHeaderValueAsString(headers,"sesionId"))
																	.dispositivo(getHeaderValueAsString(headers,"device"))
																	.idioma(getHeaderValueAsString(headers,"content-language"))
																	.portalId(getHeaderValueAsString(headers,"portalId"))
																	.uuid(getHeaderValueAsString(headers,"uuid"))
																	.ip(getHeaderValueAsString(headers,"ipaddress"))
																	.horaTransaccion(getHeaderValueAsString(headers,"transactionDate"))
																	.llaveSimetrica(getHeaderValueAsString(headers,"simetricKey"))
																	.usuario(getHeaderValueAsString(headers,"userId"))
																	.paginado(Paginado.builder()
			    																  .cantRegistros(getHeaderValueAsInt(headers,"recordsAmount"))
			    																  .numPagActual(getHeaderValueAsInt(headers,"pagesCurrentIndex"))
			    																  .numTotalPag(getHeaderValueAsInt(headers,"pagesAmount"))
			    															  .build())
																	.tags(tags)
			    											.build())
    										.dinBody(DinBody.builder()
    												        .clave(request.getPasswordAssignment().getPasswordStoredValue())
    												        .nuevaClave(request.getPasswordAssignment().getPasswordValue())
    												        .usuarioBiometrico(request.getUsername())
    												        .perfil(request.getCustomerReference().getPartyReference().getPartyType())
    												        .tipoIngreso(request.getReason())
    												        .codigoUnicoAplicacion(request.getSourceCode())
    												        .ultimosDigitosTarjeta(request.getPaymentCard().getCardNumber())
    												        .cambioPrevio(request.isPreviousChange())
    												        .token(request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue())
    														.build())
    									
    							   .build();
    	
    
    	return resp;
    }
    
    
    private UpdatePasswordAssignmentInstanceRecordRs getUpair(ResponseEntity<DinnersModelResponse> response) {
    	UpdatePasswordAssignmentInstanceRecordRs res= null;
    	DinnersModelResponse resp=response.getBody();
    	if(resp != null) {
    		res= new UpdatePasswordAssignmentInstanceRecordRs();
    		if(resp.getDinBody() != null) {
	    		res.setPasswordAssignment(PasswordAssignmentRs.builder()
										  .usageLog(UsageLog.builder()
												  			.logEntry(LogEntry.builder()
												  					 		  .logEntryDescription(resp.getDinBody().getDescripcionTransaccion())
												  					 		  .logEntryIdentification(resp.getDinBody().getCodigoTransaccion())
												  					 		  .logEntryValueDate(resp.getDinBody().getFechaTransaccion())
												  					 		  .logEntryValueTime(resp.getDinBody().getHoraTransaccion())
												  							  .build())
								  			.build())
				   .build());
    		}
    		if(resp.getDinError() != null) {
    			res.setStatusInstanceRecord(StatusInstanceRecord.builder()
						.description(resp.getDinError().getDetalle())
						.message(resp.getDinError().getMensaje())
						.providerCode(resp.getDinError().getCodigoErrorProveedor())
						.statusCode(resp.getDinError().getCodigo())
						.status(resp.getDinError().getOrigen())
						.transactionDate(resp.getDinError().getFecha())
						.statusType(resp.getDinError().getTipo())
					    .build());
    		}
			
    	}
    	
    	
    	return res;
    }
    
    
    
    
    public int getHeaderValueAsInt(HttpHeaders headers, String headerName) {
        String headerValue = headers.getFirst(headerName);
        if (headerValue != null) {
            try {
                return Integer.parseInt(headerValue);
            } catch (NumberFormatException e) {
            	log.error("El valor del encabezado no es un número válido: {}", headerValue);
            }
        }
        return 0;
     }
    
    public String getHeaderValueAsString(HttpHeaders headers, String headerName) {
        final String headerValue = headers.getFirst(headerName);
        if(headerValue != null) {
        	 return headerValue;
        }
        return "";
    }
    
} 