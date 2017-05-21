package com.itibo.project.world_of_tests.controllers;

import com.itibo.project.world_of_tests.entity.PostEntity;
import com.itibo.project.world_of_tests.entity.QuizEntity;
import com.itibo.project.world_of_tests.helpers.CurrentUser;
import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Quiz;
import com.itibo.project.world_of_tests.model.User;
import com.itibo.project.world_of_tests.service.PostService;
import com.itibo.project.world_of_tests.service.QuizService;
import com.itibo.project.world_of_tests.service.StorageService;
import com.itibo.project.world_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 26.02.2017.
 */
@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
    private final QuizService quizService;
    private final StorageService storageService;
    private final CurrentUser currentUser;

    @Autowired
    public QuizController(QuizService quizService, StorageService storageService, CurrentUser currentUser){
        this.quizService = quizService;
        this.storageService = storageService;
        this.currentUser = currentUser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllQuizzes() {
        List<Quiz> allQuizzes =  quizService.findAll();
        return new ResponseEntity<>(allQuizzes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findOneQuizById(id);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addNewQuiz(@RequestBody QuizEntity quizEntity) {
        User user = currentUser.getCurrentUser();
        Quiz quiz = toQuiz(quizEntity);
        quiz = quizService.save(quiz);
        Path quizFolder = storageService.initFolder(storageService.getPathToUserFolder(user).resolve("Quiz_" + quiz.getId()));
        if (quizEntity.getFilenames() != null && quizEntity.getFilenames().length > 0) {
            storageService.moveFiles(quizEntity.getFilenames(), storageService.getPathToTempFolder(), quizFolder);
        }
        return new ResponseEntity<>(quizEntity.getTitle(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteQuizById(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkQuizById(@RequestBody Map<String, String> params){
        Boolean result = quizService.checkQuizById(Long.parseLong(params.get("id")), params.get("answers"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Quiz toQuiz(QuizEntity quizEntity) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizEntity.getTitle());
        quiz.setDescription(quizEntity.getDescription());
        quiz.setDate(LocalDate.now().toString());
        quiz.setAuthor(currentUser.getCurrentUser());
        quiz.setJson_quiz(quizEntity.getJson_quiz());
        quiz.setJson_answer(quizEntity.getJson_answer());
        quiz.setImage(quizEntity.getImage());
        return quiz;
    }
}
