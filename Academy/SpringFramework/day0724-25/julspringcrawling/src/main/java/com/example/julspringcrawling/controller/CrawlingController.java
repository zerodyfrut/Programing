package com.example.julspringcrawling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.julspringcrawling.command.BibleCommand;
import com.example.julspringcrawling.command.NewsCommand;
import com.example.julspringcrawling.service.CrawlingService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CrawlingController {

    @Autowired
    CrawlingService service;

    @GetMapping("/itnews")
    public String sportnewsCrawling(Model m) {
        List<NewsCommand> list = service.crawling();
        m.addAttribute("list", list);
        return "crawling/result";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/crawling/form")
    public void form() {
    }

    @GetMapping("/crawling/test")
    public void bibleCrawling(@RequestParam("b") String b, Model m) {
        BibleCommand bible = service.crawling2(b);
        m.addAttribute("bibles", bible);
    }

}
