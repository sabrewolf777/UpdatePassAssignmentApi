package ec.com.dinersclub.issuedDeviceAdministration.infrastructure.inbound.controllers.grpc;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;

@Slf4j
@GrpcGlobalServerInterceptor
public class StoreServer implements ServerInterceptor{
	

	  @VisibleForTesting
	  static final Metadata.Key<String> CUSTOM_HEADER_KEY = Metadata.Key.of("custom_server_header_key", Metadata.ASCII_STRING_MARSHALLER);

	  public static final Context.Key<Object> HEADERS = Context.key("headers"); // "identity" is just for debugging


	  @Override
	  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,final Metadata requestHeaders,ServerCallHandler<ReqT, RespT> next) {
	    log.info("header received from client: {}", requestHeaders);
	    
	    /*return next.startCall(new SimpleForwardingServerCall<ReqT, RespT>(call) {
	      public void sendHeaders(Metadata responseHeaders) {
	        responseHeaders.put(CUSTOM_HEADER_KEY, "customRespondValue");
	        super.sendHeaders(responseHeaders);
	      }
	    }, requestHeaders);
	    */
	    Context context = Context.current().withValue(HEADERS, requestHeaders);
	    return Contexts.interceptCall(context, call, requestHeaders, next);
	  }
	
}