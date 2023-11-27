package learn.vk.microservices.inventoryservice.repository;

public interface InventoryRepository extends org.springframework.data.jpa.repository.JpaRepository<learn.vk.microservices.inventoryservice.entity.InventoryItem, Long> {
}
