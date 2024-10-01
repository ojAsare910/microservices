package com.ojasare.grpcservice.dto;

public record OrderRequest(Long productId, String customerName, Integer quantity) {
}
