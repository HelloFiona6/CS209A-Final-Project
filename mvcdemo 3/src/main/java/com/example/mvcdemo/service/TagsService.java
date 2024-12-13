package com.example.mvcdemo.service;

import com.example.mvcdemo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsService {
    private final TagRepository tagRepository;

    @Autowired
    public TagsService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Object[]> getTagsWithQuestionCount() {
        List<Object[]> results = tagRepository.findTagsWithQuestionCount(PageRequest.of(1, 11));
        System.out.println("results" + results.get(0).length);
        return results;
    }
}

