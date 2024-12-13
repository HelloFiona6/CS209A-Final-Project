package com.example.mvcdemo.controller;

import com.example.mvcdemo.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @RequestMapping("/")
    public String showIndexPage() {
        return "index";  // 返回的是 resources/templates/index.html 页面
    }
}