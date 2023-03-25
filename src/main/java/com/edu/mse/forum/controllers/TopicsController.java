package com.edu.mse.forum.controllers;

import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller defining a RESTful API for creating forum topics.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class TopicsController {

    private final TopicService topicService;

    @PostMapping
    public TopicDto createTopic(@RequestBody TopicDto topic) {
        return topicService.createTopic(topic);
    }

    @GetMapping
    public List<TopicDto> getAllTopics() {
        return topicService.getAllTopics();
    }

}
