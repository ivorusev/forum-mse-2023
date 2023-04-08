package com.edu.mse.forum.controllers;

import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.services.ReplyService;
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
@RequestMapping("replies")
public class RepliesController {

    private static final Logger LOGGER = LogManager.getLogger(RepliesController.class);
    private final ReplyService replyService;

    @PostMapping
    public ReplyDto createReply(@RequestBody ReplyDto reply) {
        return replyService.createReply(reply);
    }

}
