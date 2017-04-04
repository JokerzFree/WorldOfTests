package com.itibo.project.world_of_tests.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.service.UserService;
import com.itibo.project.world_of_tests.storage.StorageFileNotFoundException;
import com.itibo.project.world_of_tests.storage.StorageService;
import com.lowagie.text.pdf.PdfTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Paths;
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
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("upload-dir/"+filename));
            document.open();
            // Left
            Paragraph paragraph = new Paragraph("iText PDF Sample");
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            //
            PdfPTable pdfTable = new PdfPTable(4);
            List<User> users = userService.findAll();
            for (User user: users){
                pdfTable.addCell(user.getUsername());
                pdfTable.addCell(user.getEmail());
                pdfTable.addCell(user.getName());
                pdfTable.addCell(user.getBirthday().toString());
            }
            document.add(pdfTable);
            //
            document.close();
            //
            Resource loader = storageService.loadAsResource(filename);
            return new ResponseEntity<>(loader, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
