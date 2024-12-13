package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Questions;
import com.example.mvcdemo.model.Student;
import com.example.mvcdemo.model.Tags;
import com.example.mvcdemo.repository.QuestionRepository;
import com.example.mvcdemo.util.ErrorWordMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Object[]> getTagsWithEngagementCount() {
        List<Object[]> results = questionRepository.findTopTenTagsByEngagement(PageRequest.of(0, 11));
        System.out.println("results" + results.size());
        return results;
    }

    public List<Questions> getQuestions(){
        return questionRepository.findAll();
    }

    public Map<String, Integer> getErrorWordCounts() {
        List<Questions> questions = questionRepository.findAll();

        String fullText = "";
        String tagText;
        for (Questions question : questions) {
            String bodyText = question.getBody();
            Set<Tags>tags = question.getTags();
            fullText = fullText.concat(bodyText) ;
            for (Tags tag : tags) {
                tagText =tag.getName();
                fullText=fullText.concat(tagText);
            }
        }
        Map<String, Integer> errorCounter = ErrorWordMatcher.countErrorWords(fullText);
        errorCounter.forEach((word, count) -> System.out.println(word + ": " + count));


        return errorCounter;
    }

}
