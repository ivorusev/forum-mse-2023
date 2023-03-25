package com.edu.mse.forum.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class TopicDto {

    private String title;
    private Long userId;
    private Date createdAt;
    private Date updatedAt;

}
