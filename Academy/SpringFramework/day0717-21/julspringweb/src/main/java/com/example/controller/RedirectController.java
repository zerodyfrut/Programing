package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/re_test")
    public String logic1(){
        System.out.println("비지니스 로직 수행");
        return "redirect:/a";
        // 리다이랙트 접두어 뒤에는 컨트롤러 요청 uri를 작성
    }

}
