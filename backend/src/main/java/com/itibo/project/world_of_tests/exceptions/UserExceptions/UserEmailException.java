package com.itibo.project.world_of_tests.exceptions.UserExceptions;

/**
 * Created by Andrew on 16.05.2017.
 */
public class UserEmailException extends RuntimeException {
    public UserEmailException(String message) {
        super(message);
    }

    public UserEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
