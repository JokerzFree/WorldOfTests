package com.itibo.project.world_of_tests.exceptions.UserExceptions;

/**
 * Created by Andrew on 18.05.2017.
 */
public class UserAvatarException extends RuntimeException {
    public UserAvatarException(String message) {
        super(message);
    }

    public UserAvatarException(String message, Throwable cause) {
        super(message, cause);
    }
}