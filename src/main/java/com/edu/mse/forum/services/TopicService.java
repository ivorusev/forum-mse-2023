package com.edu.mse.forum.services;

import com.edu.mse.forum.controllers.TopicsController;
import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.mappers.ReplyMapper;
import com.edu.mse.forum.mappers.TopicsMapper;
import com.edu.mse.forum.repositories.ReplyRepository;
import com.edu.mse.forum.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private static final int PAGE_SIZE = 5;
    private static final Logger LOGGER = LogManager.getLogger(TopicsController.class);


    private final TopicRepository topicRepository;
    private final ReplyRepository replyRepository;
    private final ReplyMapper replyMapper;
    private final TopicsMapper topicsMapper;

    public TopicDto createTopic(TopicDto topicDto) {
        TopicEntity topicEntity = topicsMapper.toEntity(topicDto);
        TopicEntity savedTopic = topicRepository.save(topicEntity);
        return topicsMapper.toDto(savedTopic);
    }

    public List<TopicDto> getAllTopics(int page) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE).withPage(page);
        Page<TopicEntity> all = topicRepository.findAll(pageable);
        return all.getContent().stream().map(topicsMapper::toDto).collect(Collectors.toList());
    }

    public TopicDto getTopicById(long id, int page) {
        Optional<TopicEntity> topic = topicRepository.findById(id);
        if (!topic.isEmpty()) {
            throw new EntityNotFoundException("Could not extract topic entity with id " + id);
        }
        TopicEntity topicEntity = topic.get();
        Pageable pageWithFiveItems = PageRequest.of(page, PAGE_SIZE);
        List<ReplyEntity> allByTopic = replyRepository.findAllByTopic(topicEntity, pageWithFiveItems);
        List<ReplyDto> replyDtos = allByTopic.stream().map(replyMapper::toDto).toList();
        TopicDto topicDto = topicsMapper.toDto(topicEntity);
        topicDto.setReplies(replyDtos);
        return topicDto;
    }

    public TopicDto updateTopicWithId(long id, TopicDto topic) {
        Optional<TopicEntity> toUpdate = topicRepository.findById(id);
        if (toUpdate.isEmpty()) {
            LOGGER.info("Topic with id {} not found", id);
            throw new EntityNotFoundException("Topic not found");
        }
        TopicEntity topicEntityToUpdate = toUpdate.get();
        topicEntityToUpdate.setTitle(topic.getTitle());
        topicRepository.save(topicEntityToUpdate);
        return topicsMapper.toDto(topicEntityToUpdate);
    }

    public void deleteTopicWithId(long id) {
        Optional<TopicEntity> toDelete = topicRepository.findById(id);
        if (toDelete.isEmpty()) {
            LOGGER.info("Topic with id {} not found", id);
            throw new EntityNotFoundException("Topic not found");
        }
        topicRepository.delete(toDelete.get());
    }
}
