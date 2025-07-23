package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 있어야 매핑어노테이션 동작
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody //리턴값을 응답정보에 담아 바로 client로 전달
    public String hello(){
        return "hello 스프링!";
    }

    @GetMapping("/hello2")
    public String hello2(Model m){
        m.addAttribute("name", "김자바");
        return "view/hello2"; 
        // view name - templates의 하위 폴더부터 파일명까지 입력.
        // 해당 view 파일을 찾아가서 실행
    }

}
