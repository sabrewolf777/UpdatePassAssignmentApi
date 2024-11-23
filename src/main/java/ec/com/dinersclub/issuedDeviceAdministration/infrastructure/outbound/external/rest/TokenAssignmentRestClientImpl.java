package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.outbound.external.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinBodyOTP;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.DinHeader;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.InitiateTokenAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.MsdSegOtpRequest;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Paginado;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.Tag;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenAssignmentRestClientImpl implements TokenAssignmentRestClient {
    private final RestClient restClient;

    public TokenAssignmentRestClientImpl(RestClient.Builder builder,@Value("${msd.seg.otp.api.url}")  String baseUrl) {
        this.restClient = builder
        				  .baseUrl(baseUrl)
        				  .build();
    }

    public InitiateTokenAssignmentRs generaOTP (InitiateTokenAssignmentRq request, HttpHeaders headers) {
    
    	log.info(" **** generaOTP ***");
    	
    	final MsdSegOtpRequest req=getMsdSegOtpRequest(request,headers);    	   	
    	
    	InitiateTokenAssignmentRs InitiateTokenAssignmentRs = restClient.post().body(req).retrieve().body(InitiateTokenAssignmentRs.class);
    	
    	log.info("InitiateTokenAssignmentRs: {}",InitiateTokenAssignmentRs);
    	
    	return  InitiateTokenAssignmentRs;
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