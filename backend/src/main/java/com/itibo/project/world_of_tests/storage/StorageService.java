package com.itibo.project.world_of_tests.storage;

import com.itibo.project.world_of_tests.model.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    /**
     * Initialize folder for storage
     */
    void init();

    /**
     * Initialize folder for some User
     *
     * @param path for new folder
     */
    void initUserFolder(Path path);

    /**
     * Store file for specific user with specific name
     *
     * @param user     for which file saved
     * @param file     which would be saved
     * @param filename how everyone will see it
     */
    void store(User user, MultipartFile file, String filename);

    /**
     * Get path to all files
     *
     * @return some path
     */
    Stream<Path> loadAll();

    /**
     * Load some file from specific User with specific filename
     *
     * @param user     from which it loaded
     * @param filename which we looked for
     * @return path to this file
     */
    Path load(User user, String filename);

    /**
     * Get path to common folder
     *
     * @return path to common folder
     */
    Path getPathToCommonFolder();

    /**
     * Path to folder for specific user
     *
     * @param user which folder looking for
     * @return path to user folder
     */
    Path getPathToUserFolder(User user);

    /**
     * Load file as resource from specific user by filename
     *
     * @param user     in whose folder we looking
     * @param filename which we looking
     * @return resource
     */
    Resource loadAsResource(User user, String filename);

    /**
     * DELETE all folders and images from common folder
     * USE ONLY IF ALL DATA IS NEW
     */
    void deleteAll();

    /**
     * Delete all files from specific folder what match a specific prefix
     * @param user from which user folder delete files
     * @param prefix which try to find inside folders
     * @return boolean with success state
     */
    boolean deleteFilesFromUserFolderByPrefix(User user, String prefix);

}
