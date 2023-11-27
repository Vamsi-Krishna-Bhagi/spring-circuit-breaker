package learn.vk.microservices.orderservice.dto;

import lombok.Data;

@Data
public class InventoryItemDto {
    private Long productId;
    private Long quantity;
}
