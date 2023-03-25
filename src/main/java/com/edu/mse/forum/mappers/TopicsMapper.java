package com.edu.mse.forum.mappers;

import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.TopicEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TopicsMapper {

    TopicDto toDto(TopicEntity entity);

    TopicEntity toEntity(TopicDto dto);
}
