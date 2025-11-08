package com.homestock.group_service.exception;

public class NoPermission extends RuntimeException {
    public NoPermission(String message) {
        super(message);
    }
}
