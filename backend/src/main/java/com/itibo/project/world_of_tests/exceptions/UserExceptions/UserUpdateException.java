package com.itibo.project.world_of_tests.exceptions.UserExceptions;

/**
 * Created by Andrew on 18.05.2017.
 */
public class UserUpdateException extends RuntimeException {
    public UserUpdateException(String message) {
        super(message);
    }

    public UserUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
