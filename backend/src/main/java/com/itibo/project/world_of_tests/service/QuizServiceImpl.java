package com.itibo.project.world_of_tests.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.itibo.project.world_of_tests.helpers.JsonLibrary;
import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Quiz;
import com.itibo.project.world_of_tests.repository.PostRepository;
import com.itibo.project.world_of_tests.repository.QuizRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by Andrew on 26.02.2017.
 */
@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    private QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz findOneQuizById(Long id) {
        return quizRepository.findOneQuizById(id);
    }

    public void save(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public void deleteQuizById(Long id) {
        quizRepository.delete(id);
    }

    public boolean checkQuizById(Long id, String answer) {
        Quiz quiz = quizRepository.findOneQuizById(id);
        JSONArray j1 = new JSONArray(quiz.getJson_answer());
        JSONArray j2 = new JSONArray(answer);
        return JsonLibrary.areEqual(j1, j2);
    }
}
