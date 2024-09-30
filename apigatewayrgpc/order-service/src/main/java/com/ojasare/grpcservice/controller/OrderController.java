package com.ojasare.grpcservice.controller;

import com.google.protobuf.util.JsonFormat;
import com.ojasare.grpcservice.dto.OrderRequest;
import com.ojasare.grpcservice.dto.OrderResponse;
import com.ojasare.grpcservice.grpc.ProductResponse;
import com.ojasare.grpcservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> getProductOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        ProductResponse productResponse = orderService.getProduct(orderRequest);
        String jsonResponse = JsonFormat.printer().print(productResponse);
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long id) {
        OrderResponse orderResponse = orderService.getOrder(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

}
