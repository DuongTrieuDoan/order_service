
package com.example.microservice.order.service.domain;

import javax.annotation.Generated;
import javax.persistence.Column;
import org.springframework.data.annotation.Id;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_OrderDetail extends OrderDetail {

  private final String id;
  private final String code;
  private final String name;
  private final int quantity;
  private final Long price;
  private final Long totalAmount;
  private final String orderId;

  private AutoValue_OrderDetail(
      String id,
      String code,
      String name,
      int quantity,
      Long price,
      Long totalAmount,
      String orderId) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.quantity = quantity;
    this.price = price;
    this.totalAmount = totalAmount;
    this.orderId = orderId;
  }

  @Id
  @Override
  public String id() {
    return id;
  }

  @Column
  @Override
  public String code() {
    return code;
  }

  @Column
  @Override
  public String name() {
    return name;
  }

  @Column
  @Override
  public int quantity() {
    return quantity;
  }

  @Column
  @Override
  public Long price() {
    return price;
  }

  @Column
  @Override
  public Long totalAmount() {
    return totalAmount;
  }

  @Column
  @Override
  public String orderId() {
    return orderId;
  }

  @Override
  public String toString() {
    return "OrderDetail{"
        + "id=" + id + ", "
        + "code=" + code + ", "
        + "name=" + name + ", "
        + "quantity=" + quantity + ", "
        + "price=" + price + ", "
        + "totalAmount=" + totalAmount + ", "
        + "orderId=" + orderId
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof OrderDetail) {
      OrderDetail that = (OrderDetail) o;
      return (this.id.equals(that.id()))
           && (this.code.equals(that.code()))
           && (this.name.equals(that.name()))
           && (this.quantity == that.quantity())
           && (this.price.equals(that.price()))
           && (this.totalAmount.equals(that.totalAmount()))
           && (this.orderId.equals(that.orderId()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.id.hashCode();
    h *= 1000003;
    h ^= this.code.hashCode();
    h *= 1000003;
    h ^= this.name.hashCode();
    h *= 1000003;
    h ^= this.quantity;
    h *= 1000003;
    h ^= this.price.hashCode();
    h *= 1000003;
    h ^= this.totalAmount.hashCode();
    h *= 1000003;
    h ^= this.orderId.hashCode();
    return h;
  }

  static final class Builder extends OrderDetail.Builder {
    private String id;
    private String code;
    private String name;
    private Integer quantity;
    private Long price;
    private Long totalAmount;
    private String orderId;
    Builder() {
    }
    Builder(OrderDetail source) {
      this.id = source.id();
      this.code = source.code();
      this.name = source.name();
      this.quantity = source.quantity();
      this.price = source.price();
      this.totalAmount = source.totalAmount();
      this.orderId = source.orderId();
    }
    @Override
    public OrderDetail.Builder id(String id) {
      this.id = id;
      return this;
    }
    @Override
    public OrderDetail.Builder code(String code) {
      this.code = code;
      return this;
    }
    @Override
    public OrderDetail.Builder name(String name) {
      this.name = name;
      return this;
    }
    @Override
    public OrderDetail.Builder quantity(int quantity) {
      this.quantity = quantity;
      return this;
    }
    @Override
    public OrderDetail.Builder price(Long price) {
      this.price = price;
      return this;
    }
    @Override
    public OrderDetail.Builder totalAmount(Long totalAmount) {
      this.totalAmount = totalAmount;
      return this;
    }
    @Override
    public OrderDetail.Builder orderId(String orderId) {
      this.orderId = orderId;
      return this;
    }
    @Override
    public OrderDetail build() {
      String missing = "";
      if (id == null) {
        missing += " id";
      }
      if (code == null) {
        missing += " code";
      }
      if (name == null) {
        missing += " name";
      }
      if (quantity == null) {
        missing += " quantity";
      }
      if (price == null) {
        missing += " price";
      }
      if (totalAmount == null) {
        missing += " totalAmount";
      }
      if (orderId == null) {
        missing += " orderId";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_OrderDetail(
          this.id,
          this.code,
          this.name,
          this.quantity,
          this.price,
          this.totalAmount,
          this.orderId);
    }
  }

}
