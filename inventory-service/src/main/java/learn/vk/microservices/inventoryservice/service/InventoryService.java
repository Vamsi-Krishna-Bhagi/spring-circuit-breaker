package learn.vk.microservices.inventoryservice.service;

import learn.vk.microservices.inventoryservice.dto.InventoryItemDto;
import learn.vk.microservices.inventoryservice.entity.InventoryItem;
import learn.vk.microservices.inventoryservice.exception.NotFoundException;
import learn.vk.microservices.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryItemDto getInventoryByProductId(Long productId) {
        InventoryItem inventoryItem = inventoryRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Inventory not found"));

        InventoryItemDto inventoryItemDto = new InventoryItemDto();
        inventoryItemDto.setProductId(inventoryItem.getItemId());
        inventoryItemDto.setQuantity(inventoryItem.getQuantity());
        return inventoryItemDto;

    }

    public InventoryItemDto updateInventoryItem(InventoryItemDto inventoryItemDto) {
        InventoryItem inventoryItem = inventoryRepository.findById(inventoryItemDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Inventory not found"));
        inventoryItem.setQuantity(inventoryItemDto.getQuantity());

        inventoryItem = inventoryRepository.save(inventoryItem);
        inventoryItemDto.setQuantity(inventoryItem.getQuantity());
        return inventoryItemDto;
    }

    public InventoryItemDto createInventoryItem(InventoryItemDto inventoryItemDto) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setItemId(inventoryItemDto.getProductId());
        inventoryItem.setQuantity(inventoryItemDto.getQuantity());

        inventoryItem = inventoryRepository.save(inventoryItem);

        inventoryItemDto.setProductId(inventoryItem.getItemId());
        return inventoryItemDto;
    }
}
