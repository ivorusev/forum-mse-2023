package com.edu.mse.forum.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reply")
public class ReplyEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private TopicEntity topic;

    @Column
    private String text;

    @Column
    private Long userId;

}
