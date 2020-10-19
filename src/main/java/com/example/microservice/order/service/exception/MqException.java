package com.example.microservice.order.service.exception;

public class MqException extends RuntimeException {
    String message;

    public MqException(String message) {
        this.message = message;
    }
}
