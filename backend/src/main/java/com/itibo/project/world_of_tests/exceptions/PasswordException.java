package com.itibo.project.world_of_tests.exceptions;

/**
 * Created by Andrew on 16.05.2017.
 */
public class PasswordException extends RuntimeException {
        public PasswordException(String message) {
            super(message);
        }

        public PasswordException(String message, Throwable cause) {
            super(message, cause);
        }
}
