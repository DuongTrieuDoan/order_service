package com.example.microservice.order.service.web;

import com.example.microservice.order.service.domain.Order;
import com.example.microservice.order.service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public  OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(path = "/orders/{id}")
    @ResponseBody
    public OrderDto findById(@PathVariable String id){
        Order order = orderService.findById(id);
        return OrderDto.fromDomain(order);
    }

    @GetMapping(path = "/orders/customer/{customerId}")
    @ResponseBody
    public List<OrderDto> findByCustomerId(@PathVariable String customerId){
        List<Order> order = orderService.findByCustomerId(customerId);
        return order.stream().map(ord -> OrderDto.fromDomain(ord)).collect(Collectors.toList());
    }

    @PostMapping(path = "/orders")
    @ResponseBody
    public OrderDto create(@RequestBody CreateOrderDto orderDto){
        Order order = orderService.createOrder(orderDto.createOrder());
        return OrderDto.fromDomain(order);
    }

    @PutMapping(path = "/orders")
    @ResponseBody
    public OrderDto update(@RequestBody OrderDto orderDto){
        Order order = orderService.saveOrder(orderDto.toDomain());
        return OrderDto.fromDomain(order);
    }

}
