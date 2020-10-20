package com.example.microservice.order.service.service;

import com.example.microservice.order.service.model.Order;
import com.example.microservice.order.service.model.OrderDetail;
import com.example.microservice.order.service.model.OrderQuery;
import com.example.microservice.order.service.repo.OrderDetailRepository;
import com.example.microservice.order.service.repo.OrderRepository;
import com.example.microservice.order.service.service.impl.MessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private CustomerService customerService;
    private MessageQueueService messageQueueService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        CustomerService customerService,
                        MessageQueueService messageQueueService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerService = customerService;
        this.messageQueueService = messageQueueService;
    }

    public Order findById(String orderId) {
        return orderRepository.findById(orderId)
                              .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s is not found", orderId)));
    }

    public List<Order> findByCustomerId(String customerId){
        if (!customerService.verifyCustomer(customerId)){
            throw new EntityNotFoundException(String.format("Customer with id %s is not found", customerId));
        }
        OrderQuery orderQuery = OrderQuery.create(Optional.empty(), Optional.of(customerId), Optional.empty());
        return findByQuery(orderQuery);
    }

    public List<Order> findByQuery(OrderQuery orderQuery){
        List<Order> orderList = orderRepository.findByQuery(orderQuery);
        return orderList;
    }

    public Order save(Order order) {
        verifyCustomer(order.getCustomerId());
        findById(order.getId());
        orderRepository.deleteById(order.getId());
        return saveOrderWithOrderDetails(order);
    }

    public Order create(Order order) {
        verifyCustomer(order.getCustomerId());
        return saveOrderWithOrderDetails(order);
    }

    private Order saveOrderWithOrderDetails(Order order) {
        Order savedOrder = orderRepository.save(order);
        Iterable<OrderDetail> orderDetails = order.getOrderDetailList();
        Iterable<OrderDetail> savedOrderDetails = orderDetailRepository.saveAll(orderDetails);
        savedOrder.setOrderDetailList(StreamSupport.stream(savedOrderDetails.spliterator(), false).collect(Collectors.toList()));
        messageQueueService.publishMessage(MessageQueueService.SEND_CUSTOMER_ORDER_CONFIRMATION_QUEUE, savedOrder);
        return savedOrder;
    }

    private void verifyCustomer(String customerId) {
        if (!customerService.verifyCustomer(customerId)) {
            throw new EntityNotFoundException(String.format("Customer with id %s is not found", customerId));
        }
    }
}
