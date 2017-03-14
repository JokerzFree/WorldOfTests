package com.itibo.project.world_of_tests.storage;

/**
 * Created by Andrew on 12.03.2017.
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}