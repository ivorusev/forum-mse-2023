package com.edu.mse.forum.services;

import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.mappers.TopicsMapper;
import com.edu.mse.forum.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository repository;

    private final TopicsMapper mapper;

    public TopicDto createTopic(TopicDto topicDto) {
        TopicEntity topicEntity = mapper.toEntity(topicDto);
        TopicEntity savedTopic = repository.save(topicEntity);
        return mapper.toDto(savedTopic);
    }

    public List<TopicDto> getAllTopics() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
