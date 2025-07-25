package com.example.julspringcrawling.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Report {
    //form과 변수명 일치 시킬 것.
    private String stuno;
    private MultipartFile report;
}
