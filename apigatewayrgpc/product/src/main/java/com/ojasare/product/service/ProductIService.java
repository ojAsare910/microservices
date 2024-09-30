package com.ojasare.product.service;


import com.ojasare.product.dto.ProductRequest;
import com.ojasare.product.dto.ProductResponse;

import java.util.List;

public interface ProductIService {
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse getProduct(Long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(Long id, ProductRequest productRequest);
}
