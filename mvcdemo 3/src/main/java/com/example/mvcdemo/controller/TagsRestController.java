package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagsRestController {

    private final TagsService tagsService;

    @Autowired
    public TagsRestController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping("/toptags")
    public List<Object[]> getTagsWithQuestionCount(@RequestParam(defaultValue = "20") int num) {
        return tagsService.getTagsWithQuestionCount(num);
    }

    @GetMapping("/tagsjson")
    public List<Object[]> getTagsJson(@RequestParam(defaultValue = "20") int num) {
        return tagsService.getTagsWithQuestionCount(num);
    }
    // todo
    @GetMapping("/tag-count")
    public List<Object[]> getTagCount(@RequestParam String name) {
        return tagsService.getTagCountByTagName(name);
    }
}
