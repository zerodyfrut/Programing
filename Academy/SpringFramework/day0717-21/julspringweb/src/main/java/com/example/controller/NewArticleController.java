package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.command.Article;
import com.example.service.ArticleService;

@RequestMapping("article/newArticle")
@Controller
public class NewArticleController {
    
    @Autowired
    ArticleService service;

    @GetMapping
    public String form(){
        return"article/form";
    }

    @PostMapping
    public String submit(@ModelAttribute("command") Article article){
    
        //1. DS가 Article 객체 생성 및 주소값 전달
        //2. Mapping 어노테이션이 파라미터 값을 저장(이름을 맞춰서)
        //3. DS가 생성한 객체(Article) 모델로 추가가 됨(view로 전달)
        //   이름은 타입이름을 소문자(article)로 지정한다.
        //   이름을 바꾸고 싶으면, @ModelAttribute() 사용. 해당 이름이 view로 전달

        //System.out.println(article);
        service.printArticle(article);
        return"article/submit";
    }

}
