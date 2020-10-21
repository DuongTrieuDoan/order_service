package com.example.microservice.order.service.service;

import com.example.microservice.order.service.model.Order;
import com.example.microservice.order.service.service.impl.MessageQueueService;
import com.example.microservice.order.service.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class RabbitMessageQueueService implements MessageQueueService {

    @Override
    public <T> void publishMessage(String queue, T object){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queue, false, false, false, null);
            Optional<String> messageBody = toDtoObject(object);
            if (messageBody.isPresent()) {
                channel.basicPublish("", queue, null, messageBody.get().getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Sent '" + messageBody + "'");
            } else {
                System.out.println(" [x] Invalid object to convert");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private <T> Optional<String> toDtoObject(T object) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Object> objectDto;
        objectDto = object instanceof Order ?
                Optional.of(OrderDto.fromDomain((Order) object)) : Optional.empty();
        return objectDto.isPresent() ? Optional.of(objectMapper.writeValueAsString(objectDto.get())) : Optional.empty();
    }
}
