package ec.com.dinersclub.issuedDeviceAdministration.application.service;

import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.LogEntry;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.PasswordAssignmentRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRq;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UpdatePasswordAssignmentInstanceRecordRs;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.StatusInstanceRecord;
import ec.com.dinersclub.issuedDeviceAdministration.domain.model.UsageLog;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.DinHeader;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordServiceGrpc;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordUpdateBody;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordUpdateRequest;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordUpdateResponse;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.repository.PasswordGrpcClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PasswordServiceImpl implements PasswordService {
    
   
	//private final PasswordGrpcClient grpcClient;
/*
    public PasswordServiceImpl(PasswordGrpcClient grpcClient) {
        this.grpcClient = grpcClient;
    }
*/
    public UpdatePasswordAssignmentInstanceRecordRs passwordUpdate(UpdatePasswordAssignmentInstanceRecordRq request,HttpHeaders headers) {
        
    	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8980).usePlaintext().build();
    	/*ManagedChannel channel = NettyChannelBuilder
                .forAddress("localhost", 8980)
                .useTransportSecurity()
                .keepAliveTime(1, TimeUnit.MINUTES) 
                .keepAliveTimeout(10, TimeUnit.SECONDS)
                .build();
*/
    	PasswordServiceGrpc.PasswordServiceBlockingStub stub = PasswordServiceGrpc.newBlockingStub(channel);

    	
    	
        // Convertir request a gRPC request
        PasswordUpdateRequest grpcRequest = buildGrpcRequest(request, headers);
        
        
        PasswordUpdateResponse grpcResponse=stub.updatePassword(grpcRequest);

		log.info("grpcResponse: {} ",grpcResponse);

		channel.shutdown();
        
        
        // Llamar al servicio gRPC
        //PasswordUpdateResponse grpcResponse = grpcClient.updatePassword(grpcRequest);
        
        // Convertir respuesta gRPC a response del servicio
        return buildServiceResponse(grpcResponse);
    }

    private PasswordUpdateRequest buildGrpcRequest(UpdatePasswordAssignmentInstanceRecordRq request, HttpHeaders headers) {
       
    	return PasswordUpdateRequest.newBuilder()
            .setDinHeader(DinHeader.newBuilder()
		                 .build())
            .setDinBody(PasswordUpdateBody.newBuilder()
		                .build())
            .build();
    	
    }

    private UpdatePasswordAssignmentInstanceRecordRs buildServiceResponse(PasswordUpdateResponse grpcResponse) {
        UpdatePasswordAssignmentInstanceRecordRs response = new UpdatePasswordAssignmentInstanceRecordRs();
        
        // Verifica qué campos realmente necesitas mapear según tu modelo de dominio
        // y comenta o elimina los que no sean necesarios
        // response.setTipo(grpcResponse.getTipo());
        // response.setFecha(grpcResponse.getFecha());
        
        return response;
    }
} 