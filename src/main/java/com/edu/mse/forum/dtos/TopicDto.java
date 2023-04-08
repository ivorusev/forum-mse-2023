package com.edu.mse.forum.dtos;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TopicDto {

    private String title;
    private Long userId;
    private Date createdAt;
    private Date updatedAt;
    private List<ReplyDto> replies;

}
