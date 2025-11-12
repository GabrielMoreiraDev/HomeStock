package com.homestock.inventory_service.exception;

public class NoPermission extends RuntimeException {
    public NoPermission(String message) {
        super(message);
    }
}
