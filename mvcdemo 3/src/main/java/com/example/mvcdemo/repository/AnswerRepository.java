package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Answers;
import com.example.mvcdemo.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answers, Long> {
    @Query("SELECT t.questionId, t.answerId " +
            "FROM Answers t ")
    List<Object[]> findAnswer();

    @Query("SELECT  u.reputation, a.upVoteCount, a.isAccepted " +
            "FROM Answers a " +
            "JOIN Users u ON a.ownerUserId = u.user_id")
    List<Object[]> findAnswerReputation();

    @Query("SELECT a.commentCount, a.upVoteCount, a.isAccepted " +
            "FROM Answers a")
    List<Object[]> findAnswerStats();

    @Query("SELECT a.creationDate-q.creation_date,q.creation_date, a.creationDate, a.upVoteCount, a.isAccepted " +
            "FROM Answers a JOIN Questions q ON a.questionId = q.question_id")
    List<Object[]> findAnswerQuestionStats();
}




