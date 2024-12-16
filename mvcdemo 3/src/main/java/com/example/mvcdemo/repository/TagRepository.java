package com.example.mvcdemo.repository;

import com.example.mvcdemo.model.Tags;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tags, Integer> {
    @Query("SELECT t.name, COUNT(q) FROM Tags t " +
            "JOIN t.questions q GROUP BY t ORDER BY COUNT(q) DESC  "
            )
    List<Object[]> findTagsWithQuestionCount(PageRequest pageRequest);

    @Query("SELECT COUNT(q) FROM Questions q JOIN q.tags t WHERE t.name = ?1")
    int countQuestionsByTagName(String tagName);
}