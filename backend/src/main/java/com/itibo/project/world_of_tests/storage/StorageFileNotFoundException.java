package com.itibo.project.world_of_tests.storage;

/**
 * Created by Andrew on 12.03.2017.
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
