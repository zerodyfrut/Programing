package com.example.command;

import java.util.List;

import lombok.Data;

// command 객체 : form 데이터 저장 객체

@Data
public class Article {
    // 변수명과 form name 값이 일치해야 자동저장
    private String title;
    private String content;
    //파라미터는 String이지만 기본형 타입은 자동 변환된다.
    private int id;

    //private List<String> pet;
    private String[] pet;

}
