package com.edu.mse.forum.repositories;

import com.edu.mse.forum.entity.TopicEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    Optional<TopicEntity> findById(Long id);

}
