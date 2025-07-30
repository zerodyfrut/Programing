package com.example.julspringcrawling.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsCommand {
    
    private String text; // 헤드라인
    private String link; // 링크주소

}
