package com.example.microservice.order.service.dto;

import com.example.microservice.order.service.model.Order;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {
    private String id;
    private List<OrderDetailDto> orderDetailList;
    private Long totalAmount;
    private String customerId;

    protected OrderDto(){}

    public OrderDto(String id, List<OrderDetailDto> orderDetailList, Long totalAmount, String customerId) {
        this.id = id;
        this.orderDetailList = orderDetailList;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

    public static OrderDto fromDomain(Order order){
        return new OrderDto(order.getId(),
                            order.getOrderDetailList().stream()
                                                .map(orderDetail -> OrderDetailDto.fromDomain(orderDetail))
                                                .collect(Collectors.toList()),
                            order.getTotalAmount(),
                            order.getCustomerId());
    }

    public Order toDomain(){
        Order order = new Order(this.id, Lists.newArrayList(), this.totalAmount, this.customerId);
        order.setOrderDetailList(this.orderDetailList.stream()
                                                     .map(orderDetailDto -> orderDetailDto.createOrderDetail(order))
                                                     .collect(Collectors.toList()));
        return order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
