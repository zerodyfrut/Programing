package com.julspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.julspringsecurity.auth.SecurityUser;
import com.julspringsecurity.entity.Question;
import com.julspringsecurity.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@Controller
public class QuestionController {

    @Autowired
    QuestionService service;

    // 글 list
    @GetMapping("/questions")
    public String questions(@RequestParam(value = "p", defaultValue = "0") int p, Model m) {
        Page<Question> qlist = service.findAll(p);
        m.addAttribute("page", qlist);
        return "board/list";
    }

    // 상세보기
    @GetMapping("/question/{qno}")
    public String questionsOne(@PathVariable("qno") int qno, Model m) {
        Question q = service.findOne(qno);
        m.addAttribute("question", q);
        return "/board/article";
    }

    // 쓰기,수정
    @GetMapping("/auth/form")
    public String form(Question question) {
        return "/board/form";
    }

    @PostMapping("/auth/questions")
    public String postMethodName(Question question, @AuthenticationPrincipal SecurityUser users) {
        service.create(question, users.getUsers().getUsername());
        return "redirect:/api/questions";
    }

    @GetMapping("/auth/form/update/{qno}")
    public String updateform(@PathVariable("qno") int qno, Model m) {
        Question q = service.findOne(qno);
        m.addAttribute("question", q);
        return "/board/updateform";
    }

    @PatchMapping("/auth/questions")
    public String update(Question question, @AuthenticationPrincipal SecurityUser users) {
        service.update(question);
        return "redirect:/api/questions";
    }

    // 삭제
    @GetMapping("/auth/questions/delete/{qno}")
    public String getMethodName(@PathVariable("qno") int qno) {
        //사용자 맞는지 다시한번 검사 후
        service.delete(qno);
        return "redirect:/api/questions";
    }
    
}
