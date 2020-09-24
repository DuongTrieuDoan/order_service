
package com.example.microservice.order.service.domain;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.Column;
import org.springframework.data.annotation.Id;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_Order extends Order {

  private final String id;
  private final List<OrderDetail> orderDetailList;
  private final Long totalAmount;
  private final String customerId;

  private AutoValue_Order(
      String id,
      List<OrderDetail> orderDetailList,
      Long totalAmount,
      String customerId) {
    this.id = id;
    this.orderDetailList = orderDetailList;
    this.totalAmount = totalAmount;
    this.customerId = customerId;
  }

  @Id
  @Override
  public String id() {
    return id;
  }

  @Override
  public List<OrderDetail> orderDetailList() {
    return orderDetailList;
  }

  @Column
  @Override
  public Long totalAmount() {
    return totalAmount;
  }

  @Column
  @Override
  public String customerId() {
    return customerId;
  }

  @Override
  public String toString() {
    return "Order{"
        + "id=" + id + ", "
        + "orderDetailList=" + orderDetailList + ", "
        + "totalAmount=" + totalAmount + ", "
        + "customerId=" + customerId
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Order) {
      Order that = (Order) o;
      return (this.id.equals(that.id()))
           && (this.orderDetailList.equals(that.orderDetailList()))
           && (this.totalAmount.equals(that.totalAmount()))
           && (this.customerId.equals(that.customerId()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id.hashCode();
    h *= 1000003;
    h ^= this.orderDetailList.hashCode();
    h *= 1000003;
    h ^= this.totalAmount.hashCode();
    h *= 1000003;
    h ^= this.customerId.hashCode();
    return h;
  }

  static final class Builder extends Order.Builder {
    private String id;
    private List<OrderDetail> orderDetailList;
    private Long totalAmount;
    private String customerId;
    Builder() {
    }
    Builder(Order source) {
      this.id = source.id();
      this.orderDetailList = source.orderDetailList();
      this.totalAmount = source.totalAmount();
      this.customerId = source.customerId();
    }
    @Override
    public Order.Builder id(String id) {
      this.id = id;
      return this;
    }
    @Override
    public Order.Builder orderDetailList(List<OrderDetail> orderDetailList) {
      this.orderDetailList = orderDetailList;
      return this;
    }
    @Override
    public Order.Builder totalAmount(Long totalAmount) {
      this.totalAmount = totalAmount;
      return this;
    }
    @Override
    public Order.Builder customerId(String customerId) {
      this.customerId = customerId;
      return this;
    }
    @Override
    public Order build() {
      String missing = "";
      if (id == null) {
        missing += " id";
      }
      if (orderDetailList == null) {
        missing += " orderDetailList";
      }
      if (totalAmount == null) {
        missing += " totalAmount";
      }
      if (customerId == null) {
        missing += " customerId";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_Order(
          this.id,
          this.orderDetailList,
          this.totalAmount,
          this.customerId);
    }
  }

}
