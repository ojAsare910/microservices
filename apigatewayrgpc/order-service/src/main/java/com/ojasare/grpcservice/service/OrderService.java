package com.ojasare.grpcservice.service;

import com.ojasare.grpcservice.dto.OrderRequest;
import com.ojasare.grpcservice.dto.OrderResponse;
import com.ojasare.grpcservice.exception.NotFoundException;
import com.ojasare.grpcservice.grpc.ProductRequest;
import com.ojasare.grpcservice.grpc.ProductResponse;
import com.ojasare.grpcservice.grpc.ProductServiceGrpc;
import com.ojasare.grpcservice.model.Order;
import com.ojasare.grpcservice.repository.OrderRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.channelfactory.GrpcChannelConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderIService {

    private final OrderRepository orderRepository;
    private final ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        ManagedChannel channel = ManagedChannelBuilder.forAddress("grpc-server", 9090)
                .usePlaintext()
                .build();
        productServiceBlockingStub = ProductServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public ProductResponse getProduct(OrderRequest orderRequest) {
        ProductRequest productRequest = ProductRequest.newBuilder()
                .setOrderId(orderRequest.productId())
                .build();

        ProductResponse productResponse = productServiceBlockingStub.getOrder(productRequest);

        Order orderProduct = Order.builder()
                .name(productResponse.getName())
                .price(productResponse.getPrice())
                .description(productResponse.getDescription())
                .customerName(orderRequest.customerName())
                .quantity(orderRequest.quantity())
                .totalPrice(productResponse.getPrice() * orderRequest.quantity())
                .build();

        orderRepository.save(orderProduct);
        return productResponse;
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order with id " + orderId + " not found"));

        return OrderResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .price(order.getPrice())
                .description(order.getDescription())
                .customerName(order.getCustomerName())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    @Override
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .name(order.getName())
                        .price(order.getPrice())
                        .description(order.getDescription())
                        .customerName(order.getCustomerName())
                        .quantity(order.getQuantity())
                        .totalPrice(order.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
