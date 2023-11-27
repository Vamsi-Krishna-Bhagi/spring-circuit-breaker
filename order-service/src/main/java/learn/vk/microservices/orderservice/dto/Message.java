package learn.vk.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String message;
    private Status status;

    public enum Status {
        SUCCESS, FAILURE
    }
}
