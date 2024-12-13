package com.example.mvcdemo.service;

import com.example.mvcdemo.model.Questions;
import com.example.mvcdemo.model.Student;
import com.example.mvcdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Object[]> getTagsWithEngagementCount() {
        List<Object[]> results = questionRepository.findTopTenTagsByEngagement(PageRequest.of(1, 11));
        System.out.println("results" + results.size());
        return results;
    }

    public List<Questions> getQuestions(){
        return questionRepository.findAll();
    }


}
