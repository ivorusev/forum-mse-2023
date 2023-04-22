package com.edu.mse.forum.services;

import com.edu.mse.forum.dtos.CreateUserDto;
import com.edu.mse.forum.entity.UserEntity;
import com.edu.mse.forum.mappers.UserMapper;
import com.edu.mse.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public void createUser(CreateUserDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);
        usersRepository.save(userEntity);
    }
}
