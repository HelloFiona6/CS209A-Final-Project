package com.example.mvcdemo.service;

import com.example.mvcdemo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Object[]> getAnswerReputation() {
        return answerRepository.findAnswerReputation();
    }

    public List<Object[]> getAnswerStats() {
        return answerRepository.findAnswerStats();
    }

    public List<Object[]> getAnswerQuestionStats() {
        return answerRepository.findAnswerQuestionStats();
    }
}