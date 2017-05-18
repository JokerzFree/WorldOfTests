package com.itibo.project.world_of_tests.exceptions;

/**
 * Created by Andrew on 16.05.2017.
 */
public class EmailException extends RuntimeException {
    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
