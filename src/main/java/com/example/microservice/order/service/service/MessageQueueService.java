package com.example.microservice.order.service.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageQueueService {
     String SEND_CUSTOMER_ORDER_CONFIRMATION_QUEUE = "send_customer_order_confirmation";

    <T> void publishMessage(String queue, T object);
}
