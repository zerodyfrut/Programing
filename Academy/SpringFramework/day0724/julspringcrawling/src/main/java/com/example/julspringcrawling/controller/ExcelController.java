package com.example.julspringcrawling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.julspringcrawling.command.BookInfo;
import com.example.julspringcrawling.service.ExcelService;


@Controller
public class ExcelController {

    ExcelService service;

    @GetMapping("/title")
    public String form() {
        return "title";
    }

    @GetMapping("/bookExcel")
    public void bookExcel(Model m, BookInfo info) {
        // 서비스에서 api요청
        //service.
        //결과물 -> Excel 객체
        //Excel 다운로드
        m.addAttribute(info);

    }
    
}
