package learn.vk.microservices.orderservice.repository;

import learn.vk.microservices.orderservice.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
