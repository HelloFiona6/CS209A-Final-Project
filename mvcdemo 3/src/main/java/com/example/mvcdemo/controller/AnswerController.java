package com.example.mvcdemo.controller;
import com.example.mvcdemo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/answer-reputation")
    public List<Object[]> getAnswerReputation() {
        return answerService.getAnswerReputation();
    }

    @GetMapping("/answer-stats")
    public List<Object[]> getAnswerStats() {
        return answerService.getAnswerStats();
    }

    @GetMapping("/answer-question-stats")
    public List<Object[]> getAnswerQuestionStats() {
        return answerService.getAnswerQuestionStats();
    }
}