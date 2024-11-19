package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.grpc.ForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GrpcServerConfig {

    @Bean
    ServerInterceptor loggingInterceptor() {
    	log.info("Llamada gRPC recibida");
        return new ServerInterceptor() {

            @Override
            public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
                    ServerCall<ReqT, RespT> call,
                    Metadata headers,
                    ServerCallHandler<ReqT, RespT> next) {

            	log.info("Llamada gRPC recibida: {}", call.getMethodDescriptor().getFullMethodName());

                return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
                    @Override
                    public void sendMessage(RespT message) {
                    	log.info("Enviando respuesta: {}", message);
                        super.sendMessage(message);
                    }
                }, headers);
            }
        };
    }
}