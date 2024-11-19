package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.repository;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordServiceGrpc;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordUpdateRequest;
import ec.com.dinersclub.issuedDeviceAdministration.infrastructure.grpc.PasswordUpdateResponse;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;

//@Component
public class PasswordGrpcClient {
    
    private final ManagedChannel channel;
    private final PasswordServiceGrpc.PasswordServiceBlockingStub blockingStub;
    
    
    
    public PasswordGrpcClient(
            @Value("${grpc.server.host:localhost}") String host,
            @Value("${grpc.server.port:9090}") int port) {
        
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
                
        this.blockingStub = PasswordServiceGrpc.newBlockingStub(channel);
    }

    public PasswordUpdateResponse updatePassword(PasswordUpdateRequest request) {
        return blockingStub.updatePassword(request);
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}