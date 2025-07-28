package com.example.julspringcrawling.command;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContentCommand {
    //필드(변수)값 검사
    private int id;
    @NotEmpty(message = "작성자는 반드시 입력해야 합니다.")
    private String writer;
    @Size(min=10,max = 20,message = "10자 이상 20자 이하로 입력하세요.")
    private String content;

}
