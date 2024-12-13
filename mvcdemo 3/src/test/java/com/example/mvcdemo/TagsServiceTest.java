package com.example.mvcdemo;

import com.example.mvcdemo.model.Tags;
import com.example.mvcdemo.repository.TagRepository;
import com.example.mvcdemo.service.TagsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagsServiceTest {

    private TagsService dataService;
    private TagRepository mockTagRepository;

    @BeforeEach
    public void setUp() {
        // 创建模拟的TagRepository
        mockTagRepository = mock(TagRepository.class);

        // 初始化 TagsService，注入模拟的TagRepository
        dataService = new TagsService(mockTagRepository);

        // 这里可以设置一些mock行为，例如mockTagRepository.findTagsWithQuestionCount();
    }

    @Test
    public void testGetTagsWithQuestionCount() {
        // 设置mock的行为
        // 假设的示例数据
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{1, "Tag1", 10L});
        when(mockTagRepository.findTagsWithQuestionCount(PageRequest.of(0, 10))).thenReturn(mockResults);

        // 调用getTagsWithQuestionCount方法
        List<Object[]> tags = dataService.getTagsWithQuestionCount(10);

        // 验证结果是否符合预期
        assertNotNull(tags);
        assertFalse(tags.isEmpty());
    }
}