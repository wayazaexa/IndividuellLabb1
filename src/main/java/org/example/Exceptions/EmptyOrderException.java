package org.example.Exceptions;

public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException(String message) { super(message); }
}
