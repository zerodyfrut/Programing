package com.example.julspringcrawling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.julspringcrawling.command.BookInfo;
import com.example.julspringcrawling.service.ExcelService;


@Controller
public class ExcelController {

    @Autowired
    ExcelService service;

    @GetMapping("/title")
    public void form() {
    }

    @GetMapping("/bookExcel")
    public void bookExcel(@RequestParam("d_titl") String d_titl, Model m) {
        // 서비스에서 api요청
        BookInfo info=service.bookExcel(d_titl);
        //결과물 -> Excel 객체
        m.addAttribute("info", info);
        
        //Excel 다운로드
    }
    
}
