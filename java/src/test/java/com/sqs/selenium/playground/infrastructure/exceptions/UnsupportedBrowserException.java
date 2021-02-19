package com.sqs.selenium.playground.infrastructure.exceptions;

public class UnsupportedBrowserException extends RuntimeException {

    public UnsupportedBrowserException(final String message) {
        super(message);
    }

    public UnsupportedBrowserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    @Override
    public Throwable fillInStackTrace() {
        return null;
    }
}