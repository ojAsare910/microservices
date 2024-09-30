package com.ojasare.grpcservice.repository;

import com.ojasare.grpcservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByName(String name);
}
