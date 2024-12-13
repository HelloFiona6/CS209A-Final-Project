package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping("/top10tags")
    public List<Object[]> getTagsWithQuestionCount() {
        return tagsService.getTagsWithQuestionCount();
    }
}