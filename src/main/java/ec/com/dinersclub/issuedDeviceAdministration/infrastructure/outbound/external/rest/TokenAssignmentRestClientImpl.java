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
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinBodyOTP;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinHeader;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelRequest;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinnersModelResponse;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.MsdSegOtpRequest;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.MsdSegOtpResponse;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Paginado;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.StatusInstanceRecord;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Tag;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.TokenAssignmentOtp;

@Component
public class TokenAssignmentRestClientImpl implements TokenAssignmentRestClient {
	
    private static final Logger log = LoggerFactory.getLogger(TokenAssignmentRestClientImpl.class.getName());
    
    @Value("${msd.seg.otp.api.url}")
    String apiUrl;

    
    public InitiateTokenAssignmentRs generaOTP (InitiateTokenAssignmentRq request, HttpHeaders headers) {
    
    	log.info(" **** generaOTP ***");
    	
    	final RestTemplate restTemplate= new RestTemplate();
    	
	    final HttpHeaders hdrs = new HttpHeaders();
    	
    	final MsdSegOtpRequest req=getMsdSegOtpRequest(request,headers);    	   	

	    
	    final HttpEntity<MsdSegOtpRequest> entity = new HttpEntity<>(req, hdrs);
		
	    final ResponseEntity<MsdSegOtpResponse> response = restTemplate.exchange(apiUrl,HttpMethod.POST,entity, MsdSegOtpResponse.class);
	    
    	    	
    	log.info("InitiateTokenAssignmentRs: {}",response);
    	
    	return  dinnersToItar(response);
    }
    
    
    private InitiateTokenAssignmentRs dinnersToItar(ResponseEntity<MsdSegOtpResponse> resp) {
    	InitiateTokenAssignmentRs res=null;
    	MsdSegOtpResponse response=resp.getBody();
    	if(response != null) {
    		res= new InitiateTokenAssignmentRs();
    		 if(response.getDinBody() != null) {
    			 res.setTokenAssignment(TokenAssignmentOtp.builder()
    					 								  .description(response.getDinBody().getRespuestaSolicitud())
														  .build());
																		
    		 }
    		
			 if(response.getDinError() != null) {
				 res.setStatusInstanceRecord(StatusInstanceRecord.builder()
							.statusType(response.getDinError().getTipo())
							.transactionDate(response.getDinError().getFecha())
							.status(response.getDinError().getOrigen() )
							.statusCode(response.getDinError().getCodigo())
							.providerCode(response.getDinError().getCodigoErrorProveedor())
							.message(response.getDinError().getMensaje())
							.description(response.getDinError().getDetalle())
							.build());
			 }		
			

    	}
    	
    	
    	
    	return res;
    }
    
    
    private MsdSegOtpRequest getMsdSegOtpRequest(InitiateTokenAssignmentRq request,HttpHeaders headers) {
    	
    	final List<Tag> tags = new ArrayList<>();
    	tags.add(Tag.builder().clave(getHeaderValueAsString(headers,"tagsKeyValue"))
			        		  .valor(getHeaderValueAsString(headers,"tagsKeyValue"))
			        		  .build());
    	
    	return  MsdSegOtpRequest.builder()
    							.dinHeader(DinHeader.builder()
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
    							.dinBody(DinBodyOTP.builder()
    									.identificacionUsuario(request.getCustomerReference().getPartyReference().getReferenceId())
    									.perfil(request.getCustomerReference().getPartyReference().getPartyType())
    									.usuarioBiometrico(request.getUsername())
    									.transaccion(request.getTransactionCode())
    									.rucEmpresa(request.getOrganisation()
    													   .getOrganisationIdentification()
    													   .get(0).getIdentifier().getIdentifierValue())
    									.token(request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue())
    									.build())
    			.build();
    	
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