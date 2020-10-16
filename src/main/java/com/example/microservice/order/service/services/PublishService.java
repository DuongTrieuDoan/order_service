package com.example.microservice.order.service.services;

import com.example.microservice.order.service.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublishService {
    private JmsTemplate jmsTemplate;

    public PublishService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendOrderUpdate(Order order){
        try{
            jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
        }catch(JmsException ex){
            ex.printStackTrace();
            throw ex;
        }
    }
}
