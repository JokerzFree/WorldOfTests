package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.model.Quiz;

import java.util.List;

/**
 * Created by Andrew on 26.02.2017.
 */
public interface QuizService {
    /**
     * Find all Quiz presented into table
     *
     * @return list of quiz objects
     */
    List<Quiz> findAll();

    /**
     * Find one Quiz by unique id
     *
     * @param id unique number of quiz
     * @return Quiz object
     */
    Quiz findOneQuizById(Long id);

    /**
     * Save quiz to table
     *
     * @param quiz which would be saved
     */
    Quiz save(Quiz quiz);

    /**
     * Delete Quiz from table by unique id
     *
     * @param id unique number of quiz
     */
    void deleteQuizById(Long id);

    /**
     * Check if answer to quiz is correct
     *
     * @param id     of checked Quiz
     * @param answer which present by User
     * @return true or false
     */
    boolean checkQuizById(Long id, String answer);
}
