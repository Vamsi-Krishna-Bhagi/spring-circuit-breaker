package learn.vk.microservices.orderservice.exception;

import feign.codec.ErrorDecoder;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Component
@Log
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, feign.Response response) {
        log.info("Inside CustomErrorDecoder");
        log.info("methodKey: " + methodKey);
        log.info("response: " + response);
        String client = response.request().url().split("/")[4];
        log.info("client: " + client);
        return switch (response.status()) {
            case 400 -> new BadRequestException(client.toUpperCase() + ": Bad Request Exception");
            case 404 -> new NotFoundException(client.toUpperCase() + ": Not Found Exception");
            default -> new GenericException(client.toUpperCase() + ": Exception");
        };
    }
}
