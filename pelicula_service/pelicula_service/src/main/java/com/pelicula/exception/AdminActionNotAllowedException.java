package com.pelicula.exception;

public class AdminActionNotAllowedException extends RuntimeException {
    public AdminActionNotAllowedException(String message) {
        super(message);
    }
}
