package com.ojasare.grpcservice.service;

import com.ojasare.grpcservice.dto.ProductMessage;
import com.ojasare.grpcservice.exception.DataIntegrityViolationException;
import com.ojasare.grpcservice.exception.NotFoundException;
import com.ojasare.grpcservice.model.Product;
import com.ojasare.grpcservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductIService{

    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Override
    public void createProduct(ProductMessage productMessage) {
        if(productRepository.existsByName(productMessage.name())) {
            throw new DataIntegrityViolationException("Product with name " + productMessage.name() + " already exists.");
        }

        logger.info("Creating new product with name " + productMessage.name());
        Product product = Product.builder()
                .name(productMessage.name())
                .price(productMessage.price())
                .description(productMessage.description())
                .build();
        productRepository.save(product);
    }

    @Override
    public ProductMessage getProduct(Long id) {
       Product product = productRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Product with id " + id + " does not exist."));
        return ProductMessage.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }


}
