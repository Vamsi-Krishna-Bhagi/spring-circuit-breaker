package learn.vk.microservices.inventoryservice.dto;

import lombok.Data;

@Data
public class InventoryItemDto {
    private Long productId;
    private Long quantity;
}
