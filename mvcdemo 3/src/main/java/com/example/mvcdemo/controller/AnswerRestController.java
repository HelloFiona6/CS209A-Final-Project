package com.example.mvcdemo.controller;
import com.example.mvcdemo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AnswerRestController {
    private final AnswerService answerService;
    private List<Map<String, Object>> answerQuestionStats=null;
    private List<Map<String, Object>> answerReputation=null;

    @Autowired
    public AnswerRestController(AnswerService answerService) {
        this.answerService = answerService;
    }

//    @GetMapping("/answer-reputation")
//    public List<Object[]> getAnswerReputation() {
//        return answerService.getAnswerReputation();
//    }
//
//    @GetMapping("/answer-stats")
//    public List<Object[]> getAnswerStats() {
//        return answerService.getAnswerStats();
//    }
//
//    @GetMapping("/answer-question-stats")
//    public List<Object[]> getAnswerQuestionStats() {
//        return answerService.getAnswerQuestionStats();
//    }

    @GetMapping("/answer-reputation")
    public List<Map<String, Object>> getAnswerReputation(@RequestParam(defaultValue = "60") int num) {
        return answerService.getAnswerReputation().subList(0,num);
    }

    @GetMapping("/answer-stats")
    public List<Map<String, Object>> getAnswerStats(@RequestParam(defaultValue = "60") int num) {
        return answerService.getAnswerStats().subList(0,num);
    }

    @GetMapping("/answer-question-stats")
    public List<Map<String, Object>> getAnswerQuestionStats(@RequestParam(defaultValue = "60")int num) {
        return answerService.getAnswerQuestionStats().subList(0,num);
    }
}