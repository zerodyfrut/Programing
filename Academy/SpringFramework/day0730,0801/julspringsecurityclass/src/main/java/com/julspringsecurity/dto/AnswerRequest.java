package com.julspringsecurity.dto;

import lombok.Data;

@Data
public class AnswerRequest {
    private String content;
    private Integer questionId;
    private Integer authorId;
}
