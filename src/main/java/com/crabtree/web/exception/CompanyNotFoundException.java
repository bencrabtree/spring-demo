package com.crabtree.web.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super();
    }

    public CompanyNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
