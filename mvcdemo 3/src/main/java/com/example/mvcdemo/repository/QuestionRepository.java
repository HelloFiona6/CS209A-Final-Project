package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Questions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Questions, Long> {
    // 计算每个标签的参与度总和，并返回前十个参与度最高的标签
    @Query("SELECT tag.name,q.comment_count,q.down_vote_count,q.up_vote_count,q.answer_count,SUM(q.comment_count + q.down_vote_count + q.up_vote_count+q.answer_count) " +
            "FROM Questions q " +
            "JOIN q.tags AS tag " +
            "GROUP BY tag " +
            "ORDER BY SUM(q.comment_count + q.down_vote_count + q.up_vote_count+q.answer_count) DESC")
    List<Object[]> findTopTenTagsByEngagement(PageRequest pageRequest);

    @Query("SELECT q.body " +
            "FROM Questions q " )
    List<Questions> findErrorsCount(PageRequest pageRequest);



}




