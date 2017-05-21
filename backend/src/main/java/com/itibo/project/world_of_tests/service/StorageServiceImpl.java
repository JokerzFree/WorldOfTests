package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.exceptions.StorageExceptions.StorageException;
import com.itibo.project.world_of_tests.exceptions.StorageExceptions.StorageFileNotFoundException;
import com.itibo.project.world_of_tests.model.Quiz;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.properties.StorageProperties;
import com.itibo.project.world_of_tests.service.StorageService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;
    private final StorageProperties storageProperties;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.storageProperties = properties;
    }

    @Override
    public void store(User user, MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Path path = getPathToUserFolder(user);
            initFolder(path);
            Files.copy(file.getInputStream(), path.resolve(filename));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public void store(MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), getPathToTempFolder().resolve(filename));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path getPathFromUserFolder(User user, String filename) {
        if (filename.equals("none.png")) {
            return getPathToCommonFolder().resolve(filename);
        }
        return getPathToUserFolder(user).resolve(filename);
    }

    @Override
    public Path getPathFromQuizFolder(User user, Quiz quiz, String filename) {
        return getPathToQuizFolder(user, quiz).resolve(filename);
    }

    @Override
    public Resource loadFromUserFolder(User user, String filename) {
        try {
            Path file = getPathFromUserFolder(user, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Resource loadFromQuizFolder(User user, Quiz quiz, String filename) {
        try {
            Path file = getPathFromQuizFolder(user, quiz, filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public boolean deleteFilesFromUserFolderByPrefix(User user, String prefix) {
        boolean success = true;
        String uid = user.getId().toString();
        try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(getPathToUserFolder(user), uid + "_" + prefix + "*")) {
            for (final Path newDirectoryStreamItem : newDirectoryStream) {
                Files.delete(newDirectoryStreamItem);
            }
        } catch (final Exception e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean deleteQuizFolder(User user, Quiz quiz){
        try {
            FileUtils.deleteDirectory(getPathToQuizFolder(user, quiz).toFile());
        } catch (IOException ioEx){
            throw new StorageException("Could not find quiz directory", ioEx);
        }
        return true;
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Path initFolder(Path path) {
        Path newPath;
        try {
            newPath = Files.createDirectories(path);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
        return newPath;
    }

    @Override
    public void moveFiles(String[] filenames, Path from, Path to) {
        for (String filename : filenames) {
            moveFile(filename, from, to);
        }
    }

    @Override
    public void moveFile(String filename, Path from, Path to) {
        try {
            Files.copy(from.resolve(filename), to.resolve(filename));
        } catch (IOException ioEx) {
            throw new StorageException("Could not initialize storage", ioEx);
        }
    }

    @Override
    public Path getPathToTempFolder() {
        String temp = System.getProperty("java.io.tmpdir");
        return Paths.get(temp);
    }

    @Override
    public Path getPathToCommonFolder() {
        return Paths.get(storageProperties.getLocation());
    }

    @Override
    public Path getPathToUserFolder(User user) {
        return Paths.get(storageProperties.getLocation() + "/" + user.getUsername());
    }

    @Override
    public Path getPathToQuizFolder(User user, Quiz quiz) {
        return Paths.get(storageProperties.getLocation() + "/" + user.getUsername() + "/Quiz_" + quiz.getId());
    }
}
