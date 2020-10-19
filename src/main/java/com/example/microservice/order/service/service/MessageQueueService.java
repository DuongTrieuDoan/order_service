package com.example.microservice.order.service.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageQueueService {
     String SEND_CUSTOMER_ORDER_CONFIRMATION_QUEUE = "send_customer_order_confirmation";
//
//    public Boolean connectToMq() {
//       ConnectionFactory factory = new ConnectionFactory();
//       factory.setHost("localhost");
//       try (Connection connection = factory.newConnection();
//            Channel channel = connection.createChannel()) {
//            return true;
//       } catch (Exception ex){
//           System.out.println(ex.getMessage());
//           return false;
//       }
//    }
//
//    public void sendOrderUpdate(Order order) {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//            channel.queueDeclare(SEND_CUSTOMER_ORDER_CONFIRMATION_QUEUE, false, false, false, null);
//            String message = "The order " + order.getId() + "'s confirmation has been sent to customer";
//            channel.basicPublish("", SEND_CUSTOMER_ORDER_CONFIRMATION_QUEUE, null, message.getBytes(StandardCharsets.UTF_8));
//            System.out.println(" [x] Sent '" + message + "'");
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    public <T> void publishMessage(Consumer consumer, T object ) {
//        if (this.connectToMq()) {
//           consumer.accept(object);
//        } else {
//            System.out.println(" [x] Error MQ integration: " + object.toString() + " could not been sent to MQ");
//        }
//    }

    <T> void publishMessage(String queue, T object);
}
