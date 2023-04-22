package com.edu.mse.forum.mappers;

import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReplyMapper {

    @Mapping(target = "topicId", source = "topic.id")
    @Mapping(target = "userId", source = "userEntity.id")
    ReplyDto toDto(ReplyEntity entity);

    ReplyEntity toEntity(ReplyDto dto);
}
