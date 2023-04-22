package com.edu.mse.forum.controllers;

import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.UpdateReplyDto;
import com.edu.mse.forum.services.ReplyService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Controller defining a RESTful API for creating forum topics.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("topics")
public class RepliesController {

    private static final Logger LOGGER = LogManager.getLogger(RepliesController.class);
    private final ReplyService replyService;

    @PostMapping("{topic_id}/replies")
    public ReplyDto createReply(@PathVariable("topic_id") long topic_id, @RequestBody ReplyDto reply) {
        reply.setTopicId(topic_id);
        return replyService.createReply(reply);
    }

    @PutMapping("{topic_id}/replies/{reply_id}")
    private ReplyDto updateReply(@PathVariable("topic_id") long topicId,
                                 @PathVariable("reply_id") long replyId,
                                 @RequestBody UpdateReplyDto reply) {
        return replyService.updateReply(topicId, replyId, reply);
    }

    @DeleteMapping("{topic_id}/replies/{reply_id}")
    private void updateReply(@PathVariable("topic_id") long topicId, @PathVariable("reply_id") long replyId) {
        replyService.deleteReply(topicId, replyId);
    }

}
