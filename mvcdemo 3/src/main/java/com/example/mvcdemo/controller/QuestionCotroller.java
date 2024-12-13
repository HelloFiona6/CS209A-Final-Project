package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionCotroller {
    private final QuestionService questionService;
    @Autowired
    public QuestionCotroller(QuestionService questionService) {
        this.questionService=questionService;}

    @GetMapping("/top10engagement")
    public List<Object[]> getTagsWithEngagementCount() {
        return questionService.getTagsWithEngagementCount();
    }
}
