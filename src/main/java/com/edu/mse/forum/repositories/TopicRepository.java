package com.edu.mse.forum.repositories;

import com.edu.mse.forum.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

}
