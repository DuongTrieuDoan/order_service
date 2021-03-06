package com.example.microservice.order.service.services;

import com.example.microservice.order.service.domain.Order;
import com.example.microservice.order.service.domain.OrderDetail;
import com.example.microservice.order.service.domain.OrderQuery;
import com.example.microservice.order.service.repo.OrderDetailRepository;
import com.example.microservice.order.service.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderDetailRepository orderDetailRepository,
                        CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerService = customerService;
    }

    public Optional<Order> findById(String orderId) {
        return orderRepository.findById(orderId);
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


    public void saveOrder(Order order) throws EntityNotFoundException {
        verifyCustomer(order.getCustomerId());
        verifyOrder(order);
        saveOrderWithOrderDetails(order);
    }

    public Order createOrder(Order order) {
        verifyCustomer(order.getCustomerId());
        saveOrderWithOrderDetails(order);
        return order;
    }

    private void saveOrderWithOrderDetails(Order order) {
        orderRepository.save(order);
        Iterable<OrderDetail> orderDetails = order.getOrderDetailList();
        orderDetailRepository.saveAll(orderDetails);
    }

    private void verifyCustomer(String customerId) {
        if (!customerService.verifyCustomer(customerId)) {
            throw new EntityNotFoundException(String.format("Customer with id %s is not found", customerId));
        }
    }

    private void verifyOrder(Order order){
        Optional<Order> findOrder = findById(order.getId());
        if (!findOrder.isPresent()){
            throw new EntityNotFoundException(String.format("Order with id %s is not found", order.getId()));
        }
    }
}
