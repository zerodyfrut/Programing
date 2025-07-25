package com.example.julspringcrawling.command;

import java.util.List;

import lombok.Data;

@Data
public class BookInfo {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;
    
}
