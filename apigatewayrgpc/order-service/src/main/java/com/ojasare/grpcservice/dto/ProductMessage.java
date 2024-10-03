package com.ojasare.grpcservice.dto;

import lombok.Builder;

@Builder
public record ProductMessage(
        Long id,
        String name,
        String description,
        Double price) {
}
