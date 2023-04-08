package com.edu.mse.forum.services;

import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.mappers.ReplyMapper;
import com.edu.mse.forum.repositories.ReplyRepository;
import com.edu.mse.forum.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyMapper mapper;
    private final TopicRepository repository;

    public ReplyDto createReply(ReplyDto replyDto) {
        Optional<TopicEntity> topic = repository.findById(replyDto.getTopicId());
        if (!topic.isPresent()) {
            throw new EntityNotFoundException("Topic could not be extracted");
        }
        ReplyEntity replyEntity = mapper.toEntity(replyDto);
        replyEntity.setTopic(topic.get());
        ReplyEntity savedObject = replyRepository.save(replyEntity);
        return mapper.toDto(savedObject);
    }
}
