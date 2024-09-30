package com.ojasare.product.dto;

import lombok.Builder;

@Builder
public record ProductResponse(Long id, String name, String description, Double price) {
}
