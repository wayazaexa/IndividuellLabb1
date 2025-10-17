package org.example.Exceptions;

public class EmptyCustomerNameException extends RuntimeException {
    public EmptyCustomerNameException(String message) { super(message); }
}
