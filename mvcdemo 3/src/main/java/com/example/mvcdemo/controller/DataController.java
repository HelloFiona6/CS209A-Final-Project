package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.AnswerService;
import com.example.mvcdemo.service.QuestionService;
import com.example.mvcdemo.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class DataController {

    private final TagsService tagsService;
    private final AnswerService analyticService;
    private final QuestionService questionService;

    @Autowired
    public DataController(TagsService tagsService, AnswerService analyticService, QuestionService questionService) {
        this.tagsService = tagsService;
        this.analyticService = analyticService;
        this.questionService = questionService;
    }

    @RequestMapping("/")
    public String showIndexPage() {
        return "index";
    }
}