package com.example.microservice.order.service.domain;

import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class OrderDetail implements Serializable {
    @Id
    private String id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private int quantity;
    @Column
    private Long price;
    @Column
    private Long totalAmount;
    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    public OrderDetail(){}

    public OrderDetail(String id, String code, String name, int quantity, Long price, Long totalAmount, Order order) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.order = order;
    }

    public OrderDetail(String code, String name, int quantity, Long price, Long totalAmount, Order order) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
