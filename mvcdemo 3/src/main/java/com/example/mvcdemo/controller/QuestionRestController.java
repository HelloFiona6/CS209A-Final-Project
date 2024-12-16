package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionRestController {
    private final QuestionService questionService;
    private List<Map.Entry<String, Integer>> cachedSortedErrorWords = null;

    @Autowired
    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/topengagement")
    public List<Object[]> getTagsWithEngagementCount(@RequestParam(defaultValue = "20") int n) {
        return questionService.getTagsWithEngagementCount(n);
    }

    @GetMapping("/error-words")
    public Map<String, Integer> getErrorWordCounts(@RequestParam(defaultValue = "20") int num) {

        if (cachedSortedErrorWords == null || cachedSortedErrorWords.size() < num) {
            Map<String, Integer> error = questionService.getErrorWordCounts(num + 20);
            cachedSortedErrorWords = new ArrayList<>(error.entrySet());
        }

        Map<String, Integer> sortedError = new LinkedHashMap<>();
        for (int i = 0; i < num; i++) {
            sortedError.put(cachedSortedErrorWords.get(i).getKey(), cachedSortedErrorWords.get(i).getValue());
        }
        return sortedError;
    }

    @GetMapping("/error-word")
    public int getSpecificErrorWordCount(@RequestParam String word) {
        return questionService.getSpecificErrorWordCount(word);
    }
}
