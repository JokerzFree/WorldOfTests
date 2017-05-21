package com.itibo.project.world_of_tests.exceptions.UserExceptions;

/**
 * Created by Andrew on 19.05.2017.
 */
public class UserRegisterException extends RuntimeException {
    public UserRegisterException(String message) {
        super(message);
    }

    public UserRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}

