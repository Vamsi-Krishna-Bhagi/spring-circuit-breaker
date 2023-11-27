package learn.vk.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private List<OrderItemDto> orderItems;
    private Long userId;
    private OrderStatus orderStatus;
}
