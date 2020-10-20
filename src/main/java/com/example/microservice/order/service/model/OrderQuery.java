package com.example.microservice.order.service.model;

import com.google.auto.value.AutoValue;

import java.util.Optional;

@AutoValue
public abstract class OrderQuery {
    public abstract Optional<String> orderId();
    public abstract Optional<String> customerId();
    public abstract Optional<String> orderItemName();

    public static OrderQuery create(Optional<String> orderId, Optional<String> customerId, Optional<String> orderItemName) {
        return builder()
                .orderId(orderId)
                .customerId(customerId)
                .orderItemName(orderItemName)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_OrderQuery.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder orderId(Optional<String> orderId);

        public abstract Builder customerId(Optional<String> customerName);

        public abstract Builder orderItemName(Optional<String> orderItemName);

        public abstract OrderQuery build();
    }
}
