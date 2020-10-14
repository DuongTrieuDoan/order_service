package com.example.microservice.order.service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Order implements Serializable {
    @Id
    private String id;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetailList;
    @Column
    private Long totalAmount;
    @Column
    private String customerId;

    protected Order(){}

    public Order(String id, List<OrderDetail> orderDetailList, Long totalAmount, String customerId) {
        this.id = id;
        this.orderDetailList =  new HashSet<>(orderDetailList);
        this.totalAmount = totalAmount;
        this.customerId = customerId;
    }

    public Order(List<OrderDetail> orderDetailList, Long totalAmount, String customerId) {
        this.id = UUID.randomUUID().toString();
        this.orderDetailList = new HashSet<>(orderDetailList) ;
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
        this.orderDetailList = new HashSet<>(orderDetailList);
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
