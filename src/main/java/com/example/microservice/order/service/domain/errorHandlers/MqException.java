package com.example.microservice.order.service.domain.errorHandlers;

public class MqException extends RuntimeException {
    String message;

    public MqException(String message) {
        this.message = message;
    }
}
