package learn.vk.microservices.inventoryservice;

import jakarta.annotation.PostConstruct;
import learn.vk.microservices.inventoryservice.entity.InventoryItem;
import learn.vk.microservices.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

	@Autowired
	private InventoryRepository inventoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		inventoryRepository.save(new InventoryItem(1L, 10L));
		inventoryRepository.save(new InventoryItem(2L, 20L));
		inventoryRepository.save(new InventoryItem(3L, 30L));
	}
}
