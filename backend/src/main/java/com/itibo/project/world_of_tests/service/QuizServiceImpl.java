package com.itibo.project.world_of_tests.service;

import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Quiz;
import com.itibo.project.world_of_tests.repository.PostRepository;
import com.itibo.project.world_of_tests.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
}
