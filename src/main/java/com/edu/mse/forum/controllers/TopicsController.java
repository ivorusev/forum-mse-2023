package com.edu.mse.forum.controllers;

import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.services.TopicService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller defining a RESTful API for creating forum topics.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class TopicsController {

    private static final Logger LOGGER = LogManager.getLogger(TopicsController.class);
    private final TopicService topicService;

    @PostMapping
    public TopicDto createTopic(@RequestBody TopicDto topic) {
        return topicService.createTopic(topic);
    }

    /**
     * Gets all topic, paged.
     *
     * @param page page number, starts from 0
     * @return the extracted topic DTOs.
     */
    @GetMapping
    public List<TopicDto> getAllTopics(@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        return topicService.getAllTopics(page);
    }

    @GetMapping("{id}")
    public TopicDto getTopicById(@PathVariable("id") long id,
                                 @RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        TopicDto topicById = topicService.getTopicByIdWithReplies(id, page);
        if (topicById == null) {
            LOGGER.info("Topic with id {} was not found, returning 404.", id);
            throw new ResourceNotFoundException("Topic with id " + id + " was not found");
        }
        return topicById;
    }

    @PutMapping("{id}")
    public TopicDto updateTopicById(@PathVariable("id") long id, @RequestBody TopicDto topic) {
        try {
            return topicService.updateTopicWithId(id, topic);
        } catch (EntityNotFoundException e) {
            LOGGER.debug("Topic with id {} was not found, caught exception {}", id, e);
            LOGGER.debug("Cause :", e);
            throw new ResourceNotFoundException("Topic not found");
        }
    }


    @DeleteMapping("{id}")
    public void deleteTopicById(@PathVariable("id") long id) {
        try {
            topicService.deleteTopicWithId(id);
        } catch (EntityNotFoundException e) {
            LOGGER.debug("Topic with id {} was not found, caught exception {}", id, e);
            LOGGER.debug("Cause :", e);
            throw new ResourceNotFoundException("Topic not found");
        }
    }
}
