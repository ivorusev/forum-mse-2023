package com.edu.mse.forum.mappers;

import com.edu.mse.forum.dtos.CreateUserDto;
import com.edu.mse.forum.dtos.TopicDto;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    CreateUserDto toDto(UserEntity entity);

    UserEntity toEntity(CreateUserDto dto);

}
