package com.edu.mse.forum.repositories;

import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import com.edu.mse.forum.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

}
