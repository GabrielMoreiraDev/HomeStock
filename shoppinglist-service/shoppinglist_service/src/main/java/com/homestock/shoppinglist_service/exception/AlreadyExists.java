package com.homestock.shoppinglist_service.exception;

public class AlreadyExists extends RuntimeException {
    public AlreadyExists(String message) {
        super(message);
    }
}
