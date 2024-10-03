package com.ojasare.grpcservice.service;

import com.ojasare.grpcservice.dto.ProductMessage;

public interface ProductIService {
    void createProduct(ProductMessage product);
    ProductMessage getProduct(Long id);
}
