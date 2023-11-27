package learn.vk.microservices.orderservice;

import jakarta.annotation.PostConstruct;
import learn.vk.microservices.orderservice.dto.OrderStatus;
import learn.vk.microservices.orderservice.entity.OrderItems;
import learn.vk.microservices.orderservice.entity.Orders;
import learn.vk.microservices.orderservice.repository.OrderItemRepository;
import learn.vk.microservices.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }


    @PostConstruct
    public void init() {
        Orders order1 = new Orders();
        order1.setOrderId(1L);
        order1.setUserId(1L);
        order1.setOrderStatus(OrderStatus.CREATED);

        Orders order2 = new Orders();
        order2.setOrderId(2L);
        order2.setUserId(2L);
        order2.setOrderStatus(OrderStatus.CREATED);

        orderRepository.saveAll(Arrays.asList(order1, order2));

        OrderItems orderItems1 = new OrderItems(1L, 1L, order1);
        OrderItems orderItems2 = new OrderItems(2L, 2L, order2);

        orderItemRepository.saveAll(Arrays.asList(orderItems1, orderItems2));
    }
}
