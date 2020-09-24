
package com.example.microservice.order.service.domain;

import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_OrderQuery extends OrderQuery {

  private final Optional<String> orderId;
  private final Optional<String> customerId;
  private final Optional<String> orderItemName;

  private AutoValue_OrderQuery(
      Optional<String> orderId,
      Optional<String> customerId,
      Optional<String> orderItemName) {
    this.orderId = orderId;
    this.customerId = customerId;
    this.orderItemName = orderItemName;
  }

  @Override
  public Optional<String> orderId() {
    return orderId;
  }

  @Override
  public Optional<String> customerId() {
    return customerId;
  }

  @Override
  public Optional<String> orderItemName() {
    return orderItemName;
  }

  @Override
  public String toString() {
    return "OrderQuery{"
        + "orderId=" + orderId + ", "
        + "customerId=" + customerId + ", "
        + "orderItemName=" + orderItemName
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof OrderQuery) {
      OrderQuery that = (OrderQuery) o;
      return (this.orderId.equals(that.orderId()))
           && (this.customerId.equals(that.customerId()))
           && (this.orderItemName.equals(that.orderItemName()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.orderId.hashCode();
    h *= 1000003;
    h ^= this.customerId.hashCode();
    h *= 1000003;
    h ^= this.orderItemName.hashCode();
    return h;
  }

  static final class Builder extends OrderQuery.Builder {
    private Optional<String> orderId = Optional.empty();
    private Optional<String> customerId = Optional.empty();
    private Optional<String> orderItemName = Optional.empty();
    Builder() {
    }
    Builder(OrderQuery source) {
      this.orderId = source.orderId();
      this.customerId = source.customerId();
      this.orderItemName = source.orderItemName();
    }
    @Override
    public OrderQuery.Builder orderId(Optional<String> orderId) {
      this.orderId = orderId;
      return this;
    }
    @Override
    public OrderQuery.Builder customerId(Optional<String> customerId) {
      this.customerId = customerId;
      return this;
    }
    @Override
    public OrderQuery.Builder orderItemName(Optional<String> orderItemName) {
      this.orderItemName = orderItemName;
      return this;
    }
    @Override
    public OrderQuery build() {
      return new AutoValue_OrderQuery(
          this.orderId,
          this.customerId,
          this.orderItemName);
    }
  }

}
