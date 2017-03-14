package com.itibo.project.world_of_tests.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Andrew on 12.03.2017.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
