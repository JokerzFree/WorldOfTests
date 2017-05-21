package com.itibo.project.world_of_tests.exceptions.UserExceptions;

/**
 * Created by Andrew on 18.05.2017.
 */
public class UserDeleteException extends RuntimeException {
    public UserDeleteException(String message) {
        super(message);
    }

    public UserDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}