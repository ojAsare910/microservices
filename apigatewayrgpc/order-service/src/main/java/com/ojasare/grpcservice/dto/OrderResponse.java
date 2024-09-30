package com.ojasare.grpcservice.dto;

import lombok.Builder;

@Builder
public record OrderResponse(
        Long id,
        String name,
        String description,
        Double price,
        String customerName,
        Integer quantity,
        Double totalPrice
) { }
