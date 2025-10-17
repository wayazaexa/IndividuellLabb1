package org.example.Exceptions;

public class EmptyProductCategoryException extends RuntimeException {
    public EmptyProductCategoryException(String message) { super(message); }
}
