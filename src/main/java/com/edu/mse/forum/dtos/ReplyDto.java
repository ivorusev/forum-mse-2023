package com.edu.mse.forum.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDto {

    private long topicId;

    private long userId;

    private String text;
}
