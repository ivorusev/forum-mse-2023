package com.edu.mse.forum.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "topics")
public class TopicEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private Long userId;

    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<ReplyEntity> replies;
}