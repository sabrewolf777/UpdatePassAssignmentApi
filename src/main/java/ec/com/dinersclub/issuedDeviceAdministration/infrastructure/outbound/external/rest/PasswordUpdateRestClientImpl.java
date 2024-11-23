package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
	    
    public ResponseEntity<UpdatePasswordAssignmentInstanceRecordRs> passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
        
     	 RestTemplate restTemplate= new RestTemplate();
    	
    	 HttpHeaders hdrs = new HttpHeaders();

    	 final DinnersModelRequest dinnerRequest=getDinnerRequest(request,headers);
    	 
    	 HttpEntity<DinnersModelRequest> entity = new HttpEntity<>(dinnerRequest, hdrs);

         ResponseEntity<DinnersModelResponse> response = restTemplate.exchange(apiUrl,HttpMethod.POST,entity, DinnersModelResponse.class);

         log.info("response: {}",response);
         
         return  new ResponseEntity<>(getUpair(response),response.getStatusCode());
    }
    
    
    public DinnersModelRequest getDinnerRequest(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
    		
    	DinnersModelRequest resp= null;	
    	
    	try { 
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
    	
    		log.info("resp: {}",resp);
    		
    	}catch(Exception e) {
    		log.error("ERROR: ",e);
    	}
    	return resp;
    	
    }
    
    
    private UpdatePasswordAssignmentInstanceRecordRs getUpair(ResponseEntity<DinnersModelResponse> response) {
    	
    	UpdatePasswordAssignmentInstanceRecordRs res= null;
    	
    	if(response.getBody() != null) {
    		res= new UpdatePasswordAssignmentInstanceRecordRs();
    		if(response.getBody().getDinBody() != null) {
	    		res.setPasswordAssignment(PasswordAssignmentRs.builder()
										  .usageLog(UsageLog.builder()
												  			.logEntry(LogEntry.builder()
												  					 		  .logEntryDescription(response.getBody().getDinBody().getDescripcionTransaccion())
												  					 		  .logEntryIdentification(response.getBody().getDinBody().getCodigoTransaccion())
												  					 		  .logEntryValueDate(response.getBody().getDinBody().getFechaTransaccion())
												  					 		  .logEntryValueTime(response.getBody().getDinBody().getHoraTransaccion())
												  							  .build())
								  			.build())
				   .build());
    		}
    		if(Objects.nonNull(response.getBody().getDinError())) {
    			res.setStatusInstanceRecord(StatusInstanceRecord.builder()
						.description(response.getBody().getDinError().getDetalle())
						.message(response.getBody().getDinError().getMensaje())
						.providerCode(response.getBody().getDinError().getCodigoErrorProveedor())
						.statusCode(response.getBody().getDinError().getCodigo())
						.status(response.getBody().getDinError().getOrigen())
						.transactionDate(response.getBody().getDinError().getFecha())
						.statusType(response.getBody().getDinError().getTipo())
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
            	log.error("El valor del encabezado no es un número válido: " + headerValue);
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