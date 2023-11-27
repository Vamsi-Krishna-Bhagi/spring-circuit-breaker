package learn.vk.microservices.inventoryservice.controller;

import learn.vk.microservices.inventoryservice.dto.InventoryItemDto;
import learn.vk.microservices.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{id}")
    public InventoryItemDto getInventory(@PathVariable Long id) {
        return inventoryService.getInventoryByProductId(id);
    }

    @PutMapping
    public InventoryItemDto createInventory(@RequestBody InventoryItemDto inventoryItemDto) {
        return inventoryService.updateInventoryItem(inventoryItemDto);
    }

    @PostMapping
    public InventoryItemDto updateInventory(@RequestBody InventoryItemDto inventoryItemDto) {
        return inventoryService.createInventoryItem(inventoryItemDto);
    }
}
