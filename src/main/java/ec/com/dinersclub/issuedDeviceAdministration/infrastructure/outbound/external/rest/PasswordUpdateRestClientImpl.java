package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinBody;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinHeader;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelRequest;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelResponse;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.LogEntry;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Paginado;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PasswordAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.StatusInstanceRecord;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UsageLog;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PasswordUpdateRestClientImpl implements PasswordUpdateRestClient {

    private final RestClient restClient;

    public PasswordUpdateRestClientImpl(RestClient.Builder restClientBuilder,@Value("${password.update.api.url}") String baseUrl) {
        this.restClient = restClientBuilder
            .baseUrl(baseUrl)
            .build();
    }

    public UpdatePasswordAssignmentInstanceRecordRs passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
        	
    	DinnersModelRequest dinnerRequest=getDinnerRequest(request,headers);
    	
    	log.info("DinnersModelRequest: {}",dinnerRequest);
    	
    	DinnersModelResponse dinnerResponse = restClient.post().body(dinnerRequest).retrieve().body(DinnersModelResponse.class);
    	
    	log.info("dinnerResponse: {}",dinnerResponse);
    	
    	UpdatePasswordAssignmentInstanceRecordRs res= new UpdatePasswordAssignmentInstanceRecordRs();
    	
    	res.setPasswordAssignment(PasswordAssignmentRs.builder()
    												  .usageLog(UsageLog.builder()
    														  			.logEntry(LogEntry.builder()
    														  					 		  .logEntryDescription(dinnerResponse.getDinBody().getDescripcionTransaccion())
    														  					 		  .logEntryIdentification(dinnerResponse.getDinBody().getCodigoTransaccion())
    														  					 		  .logEntryValueDate(dinnerResponse.getDinBody().getFechaTransaccion())
    														  					 		  .logEntryValueTime(dinnerResponse.getDinBody().getHoraTransaccion())
    														  							   .build())
    														  			.build())
    												  
    												  .build());
    	res.setStatusInstanceRecord(StatusInstanceRecord.builder()
    													.description(dinnerResponse.getDetalle())
    													.message(dinnerResponse.getMensaje())
    													.providerCode(dinnerResponse.getCodigoErrorProveedor())
    													.statusCode(dinnerResponse.getCodigo())
    													.status(dinnerResponse.getOrigen())
    													.transactionDate(dinnerResponse.getFecha())
    													.statusType(dinnerResponse.getTipo())
    												    .build());
    	
    	//res.setDinHeader(dinnerResponse.getDinHeader());
    	
    	
    	log.info("res: {}",res);
    	
        return res;
    }
    
    
    public DinnersModelRequest getDinnerRequest(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
    	
    	 //List<Tag> tags = new ArrayList<>();
    	DinnersModelRequest resp= null;
    	
    	try { 
    		resp= DinnersModelRequest.builder().dinHeader(DinHeader.builder()
			    												.aplicacionId( headers.get("applicationId") != null ? headers.get("applicationId").toString() : "" ) 
			    												.canalId( headers.get("channelId") != null ? headers.get("channelId").toString() : "" )
			    												.sesionId(headers.get("sesionId") != null ? headers.get("sesionId").toString() : "" )
			    												.dispositivo(headers.get("device") != null ? headers.get("device").toString() : "")
			    												.idioma(headers.get("content-language") != null ? headers.get("content-language").toString() : "" )
			    												.portalId(headers.get("portalId") != null ? headers.get("portalId").toString() : "")
			    												.uuid(headers.get("uuid") != null ? headers.get("uuid").toString() : "")
			    												.ip(headers.get("ipaddress") != null ? headers.get("ipaddress").toString() : "")
			    												.horaTransaccion(headers.get("transactionDate") != null ? headers.get("transactionDate").toString() : "" )
			    												.llaveSimetrica(headers.get("simetricKey") != null ? headers.get("simetricKey").toString() : "")
			    												.usuario(headers.get("userId") != null ? headers.get("userId").toString() : "")
			    												.paginado(Paginado.builder()
			    																  //.cantRegistros(headers.get("recordsAmount") != null ? Integer.parseInt( headers.get("recordsAmount").toString()) : 0)
			    																  //.numTotalPag(headers.get("pagesAmount") != null ? Integer.parseInt( headers.get("pagesAmount").toString()) : 0)
			    																  //.numPagActual(headers.get("pagesCurrentIndex") != null ? Integer.parseInt(headers.get("pagesCurrentIndex").toString()) : 0)
			    																  .build())
			    												//.tags(tags)
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
    
} 