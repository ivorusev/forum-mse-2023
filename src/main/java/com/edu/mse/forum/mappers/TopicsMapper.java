package com.edu.mse.forum.mappers;

import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.TopicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TopicsMapper {

    @Mapping(target = "userId", source = "userEntity.id")
    TopicDto toDto(TopicEntity entity);

    TopicEntity toEntity(TopicDto dto);
}
