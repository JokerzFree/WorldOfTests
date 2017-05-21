package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.model.Quiz;
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
    Path initFolder(Path path);

    /**
     * Store file for specific user with specific name
     *
     * @param user     for which file saved
     * @param file     which would be saved
     * @param filename how everyone will see it
     */
    void store(User user, MultipartFile file, String filename);

    /**
     * Store file in TEMP folder
     * @param file which saved
     * @param filename with wich name saved
     */
    void store(MultipartFile file, String filename);

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
    Path getPathFromUserFolder(User user, String filename);

    /**
     * Load some file from specific Quiz folder with specific filename
     *
     * @param user     from which it loaded
     * @param quiz     from which it loaded
     * @param filename which we looked for
     * @return path to this file
     */
    Path getPathFromQuizFolder(User user, Quiz quiz, String filename);

    /**
     * Get path to temp folder
     * @return path to temp folder
     */
    Path getPathToTempFolder();

    /**
     * Move all files from array to other folder
     * @param filenames all files names
     * @param from which folder moved
     * @param to which folder moved
     */
    void moveFiles(String[] filenames, Path from, Path to);

    /**
     * Move file with filename from one folder to another
     * @param filename file name
     * @param from which folder moved
     * @param to which folder moved
     */
    void moveFile(String filename, Path from, Path to);

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
     * Path to quiz folder for specific user and quiz
     *
     * @param user which folder looking for
     * @param quiz which folder looking for
     * @return path to quiz folder
     */
    Path getPathToQuizFolder(User user, Quiz quiz);

    /**
     * Load file as resource from specific user by filename
     *
     * @param user     in whose folder we looking
     * @param filename which we looking
     * @return resource
     */
    Resource loadFromUserFolder(User user, String filename);

    /**
     * Load file as resource from specific user and quiz by filename
     *
     * @param user     in whose folder we looking
     * @param quiz     in which folder we looking
     * @param filename which we looking
     * @return resource
     */
    Resource loadFromQuizFolder(User user, Quiz quiz, String filename);

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

    /**
     * Delete quiz folder
     * @param user which folder deleted
     * @param quiz whoose deleted
     * @return success state
     */
    boolean deleteQuizFolder(User user, Quiz quiz);

}
