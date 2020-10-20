package com.example.microservice.order.service.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    private String id;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderDetail> orderDetailList = new ArrayList<>();
    @Column
    private Long totalAmount;
    @Column
    private String customerId;

    protected Order(){}

    public Order(String id, List<OrderDetail> orderDetailList, Long totalAmount, String customerId) {
        this.id = id;
        this.orderDetailList =  orderDetailList;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

    public Order(List<OrderDetail> orderDetailList, Long totalAmount, String customerId) {
        this.id = UUID.randomUUID().toString();
        this.orderDetailList = orderDetailList;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList.stream().collect(Collectors.toList());
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
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
