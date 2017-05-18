package com.itibo.project.world_of_tests.storage;

import com.itibo.project.world_of_tests.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
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
            initUserFolder(path);
            Files.copy(file.getInputStream(), path.resolve(filename));
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
    public Path load(User user, String filename) {
        if (filename.equals("none.png")){
            return getPathToCommonFolder().resolve(filename);
        }
        return getPathToUserFolder(user).resolve(filename);
    }

    @Override
    public Resource loadAsResource(User user, String filename) {
        try {
            Path file = load(user, filename);
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
    public boolean deleteFilesFromUserFolderByPrefix(User user, String prefix){
        boolean success = true;
        try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(getPathToUserFolder(user), prefix + "*")) {
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
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public void initUserFolder(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Path getPathToCommonFolder(){
        return Paths.get(storageProperties.getLocation());
    }

    @Override
    public Path getPathToUserFolder(User user) {
        return Paths.get(storageProperties.getLocation() + "/" + user.getUsername());
    }
}
