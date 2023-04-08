package com.edu.mse.forum.mappers;

import com.edu.mse.forum.dtos.ReplyDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ReplyMapper {

    ReplyDto toDto(ReplyEntity entity);

    ReplyEntity toEntity(ReplyDto dto);
}
