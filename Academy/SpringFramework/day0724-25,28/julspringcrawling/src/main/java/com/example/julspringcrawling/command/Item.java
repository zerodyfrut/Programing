package com.example.julspringcrawling.command;

import lombok.Data;

@Data
public class Item {
    //JSON 데이터 이름 ==변수명
    private String title;
    private String link;
    private String image;
    private String author;
    private int discount;
    private String publisher;
    private String pubdate;
    private long isbn;

}
