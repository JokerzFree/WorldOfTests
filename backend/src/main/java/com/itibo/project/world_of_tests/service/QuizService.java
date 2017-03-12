package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.entity.QuizEntity;
import com.itibo.project.world_of_tests.entity.UserEntity;
import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Quiz;
import com.itibo.project.world_of_tests.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by Andrew on 26.02.2017.
 */
public interface QuizService {
    List<Quiz> findAll();
    Quiz findOneQuizById(Long id);
    void save(Quiz quiz);
    void deleteQuizById(Long id);
}
