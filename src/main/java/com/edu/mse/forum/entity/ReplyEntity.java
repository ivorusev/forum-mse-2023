package com.edu.mse.forum.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reply")
@ToString
public class ReplyEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private TopicEntity topic;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}
