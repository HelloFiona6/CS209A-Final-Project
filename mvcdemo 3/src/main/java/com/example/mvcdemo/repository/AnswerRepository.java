package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
    @Query("SELECT t.questionId, t.answerId " +
            "FROM Answers t ")
    List<Object[]> findAnswer();
}




