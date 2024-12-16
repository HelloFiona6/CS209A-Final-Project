package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class QuestionRestController {
    private final QuestionService questionService;
    private List<Map.Entry<String, Integer>> cachedSortedErrorWords = null;

    @Autowired
    public QuestionRestController(QuestionService questionService) {
        this.questionService=questionService;}

    @GetMapping("/topengagement")
    public List<Object[]> getTagsWithEngagementCount(@RequestParam(defaultValue = "20") int n) {
        return questionService.getTagsWithEngagementCount(n);
    }

    @GetMapping("/error-words")
    public Map<String, Integer> getErrorWordCounts(@RequestParam(defaultValue = "20") int num) {
        if (cachedSortedErrorWords != null && cachedSortedErrorWords.size() >= num) {
            return cachedSortedErrorWords.stream()
                    .limit(num)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        cachedSortedErrorWords = questionService.getErrorWordCounts(num).entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        return questionService.getErrorWordCounts(num);
    }
    // todo
    @GetMapping("/error-word")
    public int getSpecificErrorWordCount(@RequestParam String word) {
        return questionService.getSpecificErrorWordCount(word);
    }
}
