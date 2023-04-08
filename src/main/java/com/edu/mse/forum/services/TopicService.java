package com.edu.mse.forum.services;

import com.edu.mse.forum.controllers.TopicsController;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.mappers.TopicsMapper;
import com.edu.mse.forum.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
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


    private final TopicRepository repository;

    private final TopicsMapper mapper;

    public TopicDto createTopic(TopicDto topicDto) {
        TopicEntity topicEntity = mapper.toEntity(topicDto);
        TopicEntity savedTopic = repository.save(topicEntity);
        return mapper.toDto(savedTopic);
    }

    public List<TopicDto> getAllTopics(int page) {
        Pageable pageable = Pageable.ofSize(PAGE_SIZE).withPage(page);;
        Page<TopicEntity> all = repository.findAll(pageable);
        return all.getContent().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public TopicDto getTopicById(long id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    public TopicDto updateTopicWithId(long id, TopicDto topic) {
        Optional<TopicEntity> toUpdate = repository.findById(id);
        if (toUpdate.isEmpty()) {
            LOGGER.info("Topic with id {} not found", id);
            throw new EntityNotFoundException("Topic not found");
        }
        TopicEntity topicEntityToUpdate = toUpdate.get();
        topicEntityToUpdate.setTitle(topic.getTitle());
        repository.save(topicEntityToUpdate);
        return mapper.toDto(topicEntityToUpdate);
    }

    public void deleteTopicWithId(long id) {
        Optional<TopicEntity> toDelete = repository.findById(id);
        if (toDelete.isEmpty()) {
            LOGGER.info("Topic with id {} not found", id);
            throw new EntityNotFoundException("Topic not found");
        }
        repository.delete(toDelete.get());
    }
}
