package com.Indrajit.ecommerce_api.service;

import com.Indrajit.ecommerce_api.dto.OrderItemResponse;
import com.Indrajit.ecommerce_api.exceptions.ResourceNotFoundException;
import com.Indrajit.ecommerce_api.model.Order;
import com.Indrajit.ecommerce_api.model.OrderItem;
import com.Indrajit.ecommerce_api.dto.OrderResponse;
import com.Indrajit.ecommerce_api.model.Product;
import com.Indrajit.ecommerce_api.model.User;
import com.Indrajit.ecommerce_api.repository.OrderRepository;
import com.Indrajit.ecommerce_api.repository.ProductRepository;
import com.Indrajit.ecommerce_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public OrderResponse getOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No order found with ID "+id));
        return this.mapToResponse(order);
    }

    public List<OrderResponse> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse) //.map(order -> this.mapToResponse(order))

                .collect(Collectors.toList());
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderResponse(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getUser().getName(),
                itemResponses
        );
    }
    public OrderResponse createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());

        double total = 0.0;
        for (OrderItem item : order.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with id " + item.getProduct().getId()));

            item.setPrice(product.getPrice() * item.getQuantity());
            item.setOrder(order);
            total += item.getPrice();
        }

        order.setTotalAmount(total);
        order.setStatus("pending");
        User user = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + order.getUser().getId()));

        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    public OrderResponse updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return mapToResponse(updatedOrder);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }
}
