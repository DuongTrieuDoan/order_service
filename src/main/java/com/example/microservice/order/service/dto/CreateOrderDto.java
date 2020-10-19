package com.example.microservice.order.service.dto;

import com.example.microservice.order.service.domain.Order;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderDto {
    private List<OrderDetailDto> orderDetailList;
    private Long totalAmount;
    private String customerId;

    protected CreateOrderDto(){}

    public CreateOrderDto(List<OrderDetailDto> orderDetailList, Long totalAmount, String customerId) {
        this.orderDetailList = orderDetailList;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

    public Order createOrder() {
        Order order = new Order(Lists.newArrayList(), this.totalAmount, this.customerId);
        order.setOrderDetailList(this.orderDetailList.stream()
                                                     .map(orderDetailDto -> orderDetailDto.createOrderDetail(order))
                                                     .collect(Collectors.toList()));
        return order;
    }

    public Order toDomain(){
        return null;
    }

    public List<OrderDetailDto> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailDto> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
