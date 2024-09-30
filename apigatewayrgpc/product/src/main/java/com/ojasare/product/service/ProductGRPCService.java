package com.ojasare.product.service;

import com.ojasare.product.exception.NotFoundException;
import com.ojasare.product.grpc.ProductRequest;
import com.ojasare.product.grpc.ProductResponse;
import com.ojasare.product.grpc.ProductServiceGrpc;
import com.ojasare.product.model.Product;
import com.ojasare.product.repository.ProductRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProductGRPCService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository productRepository;

    public ProductGRPCService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void getOrder(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = productRepository.findById(request.getOrderId())
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + request.getOrderId()));
        ProductResponse orderResponse = ProductResponse.newBuilder()
                .setId(product.getId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice())
                .build();
        responseObserver.onNext(orderResponse);
        responseObserver.onCompleted();
    }
}
