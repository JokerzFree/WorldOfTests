package com.itibo.project.world_of_tests.exceptions.UserExceptions;

/**
 * Created by Andrew on 16.05.2017.
 */
public class UserPasswordException extends RuntimeException {
        public UserPasswordException(String message) {
            super(message);
        }

        public UserPasswordException(String message, Throwable cause) {
            super(message, cause);
        }
}
