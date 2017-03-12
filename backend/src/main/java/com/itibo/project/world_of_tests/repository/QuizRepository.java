package com.itibo.project.world_of_tests.repository;

import com.itibo.project.world_of_tests.model.Post;
import com.itibo.project.world_of_tests.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Created by Andrew on 26.02.2017.
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT q FROM Quiz q WHERE q.id = ?1")
    Quiz findOneQuizById(Long id);

    @Modifying
    @Query(value = "DELETE FROM Quiz q WHERE q.id = ?1")
    void delete(Long id);
}
