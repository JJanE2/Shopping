package com.my.shopping.exception;

public class OrderProductNotFoundException extends RuntimeException {
    public OrderProductNotFoundException(String message) {
        super(message);
    }
}
