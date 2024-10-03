package com.ojasare.grpcservice.service;

import com.ojasare.grpcservice.dto.OrderRequest;
import com.ojasare.grpcservice.dto.OrderResponse;
import com.ojasare.grpcservice.grpc.ProductResponse;

import java.util.List;

public interface OrderIService {
    ProductResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrder(Long orderId);
    List<OrderResponse> getOrders();
}
