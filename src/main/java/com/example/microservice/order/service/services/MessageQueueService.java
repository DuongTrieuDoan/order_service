package com.example.microservice.order.service.services;

import com.example.microservice.order.service.domain.Order;
import com.example.microservice.order.service.domain.errorHandlers.MqException;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;

@Service
public class MessageQueueService {
    private final static String QUEUE_NAME = "send_customer_order_confirmation";

    public void sendOrderUpdate(Order order) throws MqException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Your order " + order.getId() + "has been saved and sent to our warehouse";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception exp) {
            throw new MqException("Error MQ integration");
        }
    }
}
