package learn.vk.microservices.orderservice.service;

import learn.vk.microservices.orderservice.dto.InventoryItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", url = "${url.inventory-service}")
public interface InventoryClientService {

    @GetMapping("{id}")
    InventoryItemDto getInventoryItem(@PathVariable Long id);

    @PutMapping
    void updateInventoryItem(@RequestBody InventoryItemDto inventoryItemDto);
}
