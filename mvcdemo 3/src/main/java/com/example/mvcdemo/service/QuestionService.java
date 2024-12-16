package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Questions;
import com.example.mvcdemo.model.Tags;
import com.example.mvcdemo.repository.QuestionRepository;
import com.example.mvcdemo.util.ErrorWordMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Object[]> getTagsWithEngagementCount(int n) {
        List<Object[]> results = questionRepository.findTopTenTagsByEngagement(PageRequest.of(0, n + 1));
        System.out.println("results" + results.size());
        List<Object[]> newResults = new ArrayList<>();

        if (results.size() > 1) {
            newResults.addAll(results.subList(1, results.size()));
        }
        return newResults;
    }

    public List<Questions> getQuestions() {
        return questionRepository.findAll();
    }

    public Map<String, Integer> getErrorWordCounts(int num) {
        List<Questions> questions = questionRepository.findAll();

        String fullText = "";
        String tagText;
        for (Questions question : questions) {
            String bodyText = question.getBody();
            Set<Tags> tags = question.getTags();
            fullText = fullText.concat(bodyText);
            for (Tags tag : tags) {
                tagText = tag.getName();
                fullText = fullText.concat(tagText);
            }
        }
        Map<String, Integer> errorCounter = ErrorWordMatcher.countErrorWords(fullText,num);
        errorCounter.forEach((word, count) -> System.out.println(word + ": " + count));


        return errorCounter;
    }
    public int getSpecificErrorWordCount(String errorWord) {
        StringBuilder fullText = new StringBuilder();
        for (Questions question : questionRepository.findAll()) {
            String bodyText = question.getBody();
            fullText.append(bodyText);
            for (Tags tag : question.getTags()) {
                fullText.append(tag.getName());
            }
        }
        return ErrorWordMatcher.countSpecificErrorWords(fullText.toString(), errorWord);
    }

}
