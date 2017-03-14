package com.itibo.project.world_of_tests.controllers;

import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.service.UserService;
import com.itibo.project.world_of_tests.storage.StorageFileNotFoundException;
import com.itibo.project.world_of_tests.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.sql.Timestamp;

/**
 * Created by Andrew on 12.03.2017.
 */
@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(origins = "http://localhost:3000")
public class UploaderController {
    private final StorageService storageService;
    private final UserService userService;
    private final CurrentUser currentUser;

    @Autowired
    public UploaderController(StorageService storageService, UserService userService, CurrentUser currentUser) {
        this.storageService = storageService;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @RequestMapping(value = "/images/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImagePath(@PathVariable String filename) {
        try {
            Resource loader = storageService.loadAsResource(filename);
            return new ResponseEntity<>(loader, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/quizes/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getQuizPath(@PathVariable String filename) {
        try {
            Resource loader = storageService.loadAsResource(filename);
            return new ResponseEntity<>(loader, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/images/upload", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {
        String filename = buildFileName(file);
        storageService.store(file, filename);
        userService.updateAvatar(currentUser.getCurrentUser(), filename);
        return new ResponseEntity(HttpStatus.OK);
    }

    private String buildFileName(MultipartFile file){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String[] fileNameParts = file.getOriginalFilename().split("\\.");
        String format = fileNameParts[fileNameParts.length-1];
        return currentUser.getCurrentUser().getId()+"_"+timestamp.getTime()+"."+format;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
