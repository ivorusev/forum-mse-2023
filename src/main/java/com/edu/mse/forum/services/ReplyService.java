package com.edu.mse.forum.services;

import com.edu.mse.forum.controllers.TopicsController;
import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.dtos.UpdateReplyDto;
import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.entity.UserEntity;
import com.edu.mse.forum.mappers.ReplyMapper;
import com.edu.mse.forum.repositories.ReplyRepository;
import com.edu.mse.forum.repositories.TopicRepository;
import com.edu.mse.forum.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReplyService {

    private static final Logger LOGGER = LogManager.getLogger(ReplyService.class);
    private final ReplyRepository replyRepository;
    private final ReplyMapper mapper;
    private final TopicRepository repository;
    private final UsersRepository usersRepository;

    public ReplyDto createReply(ReplyDto replyDto) {
        Optional<TopicEntity> topic = repository.findById(replyDto.getTopicId());
        if (topic.isEmpty()) {
            throw new EntityNotFoundException("Topic could not be extracted");
        }
        Optional<UserEntity> userOptional = usersRepository.findById(replyDto.getUserId());
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User for the reply could not be extracted");
        }

        ReplyEntity replyEntity = mapper.toEntity(replyDto);
        replyEntity.setTopic(topic.get());
        replyEntity.setUserEntity(userOptional.get());
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
