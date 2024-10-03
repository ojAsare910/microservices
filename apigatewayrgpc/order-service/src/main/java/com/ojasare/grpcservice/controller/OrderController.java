package com.ojasare.grpcservice.controller;

import com.ojasare.grpcservice.dto.OrderRequest;
import com.ojasare.grpcservice.dto.OrderResponse;
import com.ojasare.grpcservice.dto.ProductMessage;
import com.ojasare.grpcservice.grpc.ProductResponse;
import com.ojasare.grpcservice.service.OrderService;
import com.ojasare.grpcservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/order")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        ProductResponse productResponse = orderService.createOrder(orderRequest);
        if (productResponse != null) {
            return new ResponseEntity<>("Order Added Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Order Not Saved", HttpStatus.BAD_REQUEST);
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

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductMessage> getProduct(@PathVariable("id") Long id) {
        ProductMessage productMessage = productService.getProduct(id);
        return ResponseEntity.ok(productMessage);
    }

}
