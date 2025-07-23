package com.example.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.command.Article;

@Service
public class ArticleService {
    public void printArticle(Article article){
        System.out.println("id : "+article.getId()+", title : "+article.getTitle());
        System.out.println(Arrays.toString(article.getPet()));
        //System.out.println(article.getPet()); // 배열의 주소값


    }
}
