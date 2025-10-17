package org.example.Exceptions;

public class EmptyProductNameException extends RuntimeException {
    public EmptyProductNameException(String message) { super(message); }
}
