package com.crabtree.web.exception;

public class CompanyMismatchException extends RuntimeException {
    public CompanyMismatchException() {
        super();
    }

    public CompanyMismatchException(final String message, final Throwable cause) {
            super(message, cause);
        }
}
