package com.example.microservice.order.service.dto;

import com.example.microservice.order.service.model.Order;
import com.example.microservice.order.service.model.OrderDetail;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailDto {
    private String id;
    private String code;
    private String name;
    private int quantity;
    private Long price;
    private Long totalAmount;

    protected OrderDetailDto(){}

    public OrderDetailDto(String id, String code, String name, int quantity, Long price, Long totalAmount) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
    }

    public static OrderDetailDto fromDomain(OrderDetail orderDetail){
        return new OrderDetailDto(orderDetail.getId(),
                                  orderDetail.getCode(),
                                  orderDetail.getName(),
                                  orderDetail.getQuantity(),
                                  orderDetail.getPrice(),
                                  orderDetail.getTotalAmount());
    }

    public OrderDetail createOrderDetail(Order order){
        return new OrderDetail(this.code,
                               this.name,
                               this.quantity,
                               this.price,
                               this.totalAmount,
                               order);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }
}
