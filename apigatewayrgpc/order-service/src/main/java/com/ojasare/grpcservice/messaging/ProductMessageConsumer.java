package com.ojasare.grpcservice.messaging;

import com.ojasare.grpcservice.dto.ProductMessage;
import com.ojasare.grpcservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductMessageConsumer {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    private final ProductService productService;

    @RabbitListener(queues = "#{queue}")
    public void consumeMessage(ProductMessage productMessage) {
        productService.createProduct(productMessage);
    }

}
