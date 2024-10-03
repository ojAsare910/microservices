package com.ojasare.product.service;

import com.ojasare.product.dto.ProductRequest;
import com.ojasare.product.dto.ProductResponse;
import com.ojasare.product.exception.DataIntegrityViolationException;
import com.ojasare.product.exception.NotFoundException;
import com.ojasare.product.model.Product;
import com.ojasare.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductIService{

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRepository.existsByName(productRequest.name())) {
            throw new DataIntegrityViolationException("Product with name " + productRequest.name() + " already exists.");
        }
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        Product savedProduct = productRepository.save(product);
        logger.info("Creating new product");

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .build();
    }

    @Override
    public ProductResponse getProduct(Long id) {
        logger.info("Fetching product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        logger.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product ->
                        new ProductResponse(
                                product.getId(), product.getName(),
                                product.getDescription(),
                                product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        logger.info("Updating product with id: {}", id);

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));
        existingProduct.setName(productRequest.name());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setPrice(productRequest.price());
        Product updatedProduct = productRepository.save(existingProduct);
        return new ProductResponse(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice());
    }

}