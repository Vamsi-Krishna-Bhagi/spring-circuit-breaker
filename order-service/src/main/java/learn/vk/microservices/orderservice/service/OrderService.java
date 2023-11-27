package learn.vk.microservices.orderservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import learn.vk.microservices.orderservice.dto.InventoryItemDto;
import learn.vk.microservices.orderservice.dto.OrderDto;
import learn.vk.microservices.orderservice.dto.OrderItemDto;
import learn.vk.microservices.orderservice.entity.Orders;
import learn.vk.microservices.orderservice.exception.GenericException;
import learn.vk.microservices.orderservice.exception.NotFoundException;
import learn.vk.microservices.orderservice.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClientService inventoryClientService;

    public OrderService(OrderRepository orderRepository, InventoryClientService inventoryClientService) {
        this.orderRepository = orderRepository;
        this.inventoryClientService = inventoryClientService;
    }

    public OrderDto getOrderById(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
        OrderDto orderDto = new OrderDto();

        BeanUtils.copyProperties(orders, orderDto);
        orderDto.setOrderItems(orders.getOrderItems().stream().map(orderItems -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            BeanUtils.copyProperties(orderItems, orderItemDto);
            return orderItemDto;
        }).toList());

        return orderDto;
    }

    @CircuitBreaker(name = "inventory-service", fallbackMethod = "createOrderFallback")
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.getOrderItems()
                .forEach(orderItemDto -> {
                    InventoryItemDto inventoryItemDto =
                            inventoryClientService.getInventoryItem(orderItemDto.getItemId());
                    if (inventoryItemDto.getQuantity() < orderItemDto.getQuantity()) {
                        throw new GenericException("Insufficient quantity");
                    }
                    inventoryItemDto.setQuantity(inventoryItemDto.getQuantity() - orderItemDto.getQuantity());
                    inventoryClientService.updateInventoryItem(inventoryItemDto);
                });
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderDto, orders);

        orders = orderRepository.save(orders);
        orderDto.setOrderId(orders.getOrderId());
        return orderDto;
    }

    public OrderDto createOrderFallback(OrderDto orderDto, Throwable throwable) {
        throw new GenericException("Inventory service is down");
    }
}
