package com.example.mvcdemo.service;

import com.example.mvcdemo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsService {
    private final TagRepository tagRepository;

    @Autowired
    public TagsService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Object[]> getTagsWithQuestionCount(int n) {
        List<Object[]> results = tagRepository.findTagsWithQuestionCount(PageRequest.of(0, n));
        // 创建一个新的列表来存储除了第一位之外的所有元素
        List<Object[]> newResults = new ArrayList<>();

        // 检查原始列表是否有足够的元素来跳过第一位
        if (results.size() > 1) {
            // 使用 subList 方法从索引 1 开始到列表末尾
            newResults.addAll(results.subList(1, results.size()));
        }

        return newResults;
    }
}

