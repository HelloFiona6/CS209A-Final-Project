package com.example.mvcdemo.service;

import com.example.mvcdemo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

//    public List<Object[]> getAnswerReputation() {
//        return answerRepository.findAnswerReputation();
//    }
//
//    public List<Object[]> getAnswerStats() {
//        return answerRepository.findAnswerStats();
//    }
//
//    public List<Object[]> getAnswerQuestionStats() {
//        return answerRepository.findAnswerQuestionStats();
//    }

    public List<Map<String, Object>> getAnswerReputation() {

        List<Object[]> reputationData = answerRepository.findAnswerReputation();
        List<Map<String, Object>> labeledData = new ArrayList<>();

        for (Object[] data : reputationData) {
            Map<String, Object> labeledEntry = Map.of(
                    "reputation", data[0],
                    "upVoteCount", data[1],
                    "isAccepted", data[2]
            );
            labeledData.add(labeledEntry);
        }
        labeledData.sort((entry1, entry2) ->
                ((Integer) entry2.get("reputation")).compareTo((Integer) entry1.get("reputation"))
        );
        return labeledData;
    }

    public List<Map<String, Object>> getAnswerStats() {
        List<Object[]> statsData = answerRepository.findAnswerStats();
        List<Map<String, Object>> statsList = new ArrayList<>();

        for (Object[] data : statsData) {
            Map<String, Object> statsMap = Map.of(
                    "commentCount", data[0],
                    "upVoteCount", data[1],
                    "isAccepted", data[2]
            );
            statsList.add(statsMap);
        }
        statsList.sort((entry1, entry2) ->
                ((Integer) entry2.get("commentCount")).compareTo((Integer) entry1.get("commentCount")));
        return statsList;
    }

    public List<Map<String, Object>> getAnswerQuestionStats() {
        List<Object[]> statsData = answerRepository.findAnswerQuestionStats();
        List<Map<String, Object>> statsList = new ArrayList<>();

        for (Object[] data : statsData) {
            Map<String, Object> statsMap = Map.of(
                    "Elapsed time", data[0],
                    "Question Creation Date", data[1],
                    "Answer Creation Date", data[2],
                    "Up Vote Count", data[3],
                    "Is Answer Accepted", data[4]
            );
            statsList.add(statsMap);
        }
        statsList.sort((map1, map2) -> {
            Integer days1 = (Integer) map1.get("Elapsed time");
            Integer days2 = (Integer) map2.get("Elapsed time");
            return days1.compareTo(days2);
        });

        return statsList;
    }
}