package com.itibo.project.world_of_tests.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.helpers.StringLibrary;
import com.itibo.project.world_of_tests.model.Quiz;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.service.QuizService;
import com.itibo.project.world_of_tests.service.UserService;
import com.itibo.project.world_of_tests.exceptions.StorageExceptions.StorageFileNotFoundException;
import com.itibo.project.world_of_tests.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Andrew on 12.03.2017.
 */
@RestController
@RequestMapping("/api/uploads")
@CrossOrigin(origins = "http://localhost:3000")
public class UploaderController {
    private final StorageService storageService;
    private final UserService userService;
    private final QuizService quizService;
    private final CurrentUser currentUser;

    @Autowired
    public UploaderController(StorageService storageService, UserService userService, QuizService quizService, CurrentUser currentUser) {
        this.storageService = storageService;
        this.userService = userService;
        this.quizService = quizService;
        this.currentUser = currentUser;
    }

    @RequestMapping(value = "/userFiles/{id}/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getUserFile(@PathVariable Long id, @PathVariable String filename) {
        try {
            User user = userService.findUser(id);
            Resource loader = storageService.loadFromUserFolder(user, filename);
            return new ResponseEntity<>(loader, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/quizFiles/{id}/{filename:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getQuizFile(@PathVariable Long id, @PathVariable String filename) {
        try {
            Quiz quiz = quizService.findOneQuizById(id);
            User user = quiz.getAuthor();
            Resource loader = storageService.loadFromQuizFolder(user, quiz, filename);
            return new ResponseEntity<>(loader, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/files/upload", method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        User user = currentUser.getCurrentUser();
        String filename = StringLibrary.buildFileName(user, type, file);
        switch (type){
            case "avatar":
                storageService.deleteFilesFromUserFolderByPrefix(user, type);
                storageService.store(user, file, filename);
                userService.updateAvatar(currentUser.getCurrentUser(), filename);
                break;
            case "quiz":
                storageService.store(file, filename);
                break;
        }
        return new ResponseEntity<>(filename, HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
