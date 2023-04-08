package com.edu.mse.forum.repositories;

import com.edu.mse.forum.entity.ReplyEntity;
import com.edu.mse.forum.entity.TopicEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

    Optional<ReplyEntity> findById(Long id);

    List<ReplyEntity> findAllByTopic(TopicEntity topicEntity, Pageable pageable);

}
