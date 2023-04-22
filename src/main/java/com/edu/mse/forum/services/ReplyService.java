package com.edu.mse.forum.services;

import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.UpdateReplyDto;
import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.entity.UserEntity;
import com.edu.mse.forum.mappers.ReplyMapper;
import com.edu.mse.forum.repositories.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReplyService {

    private static final int PAGE_SIZE = 5;
    private static final Logger LOGGER = LogManager.getLogger(ReplyService.class);
    private final ReplyRepository replyRepository;
    private final ReplyMapper mapper;
    private final UserService userService;
    private final TopicService topicService;

    public ReplyDto createReply(ReplyDto replyDto) {
        UserEntity user = userService.getUserByIdOrFail(replyDto.getUserId());
        TopicEntity topic = topicService.getTopicById(replyDto.getTopicId());

        ReplyEntity replyEntity = mapper.toEntity(replyDto);
        replyEntity.setTopic(topic);
        replyEntity.setUserEntity(user);

        ReplyEntity savedObject = replyRepository.save(replyEntity);
        return mapper.toDto(savedObject);
    }

    public ReplyDto updateReply(long topicId, long replyId, UpdateReplyDto reply) {
        ReplyEntity replyEntity = getReplyIfExisting(replyId);
        replyEntity.setText(reply.getText());
        return mapper.toDto(replyRepository.save(replyEntity));
    }

    public void deleteReply(long topicId, long replyId) {
        ReplyEntity reply = getReplyIfExisting(replyId);
        checkIfReplyIsInTopic(topicId, reply);
        replyRepository.delete(reply);
    }

    public List<ReplyEntity> getAllRepliesByTopic(TopicEntity topicEntity, int page) {
        Pageable pageWithFiveItems = PageRequest.of(page, PAGE_SIZE);
        return replyRepository.findAllByTopic(topicEntity, pageWithFiveItems);
    }

    private ReplyEntity getReplyIfExisting(Long id) {
        Optional<ReplyEntity> replyOptional = replyRepository.findById(id);
        if (replyOptional.isEmpty()) {
            throw new EntityNotFoundException("Reply could not be extracted");
        }
        return replyOptional.get();
    }

    private void checkIfReplyIsInTopic(long topicId, ReplyEntity reply) {
        Long existingTopicId = reply.getTopic().getId();
        if (existingTopicId != topicId) {
            LOGGER.warn("Reply {} is not under topic with id {}", reply.getId(), existingTopicId);
            throw new EntityNotFoundException("Reply with id " + reply.getId() + " is not from topic with id" + topicId);
        }
    }
}
