package com.example.julspringcrawling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.julspringcrawling.command.ContentCommand;

@Controller
@RequestMapping("/valid")
public class ValidatorController {

    private final CrawlingController crawlingController;

    ValidatorController(CrawlingController crawlingController) {
        this.crawlingController = crawlingController;
    }

    @GetMapping("/insertForm")
    public void form(@ModelAttribute("command") ContentCommand command) {
        // form.html에서 ${command}를 사용하고 있기에 빈 객체라도 넘겨줘야함.

    }

    @GetMapping("/create")
    public String submit(@ModelAttribute("command") @Validated ContentCommand command, BindingResult error) {
        // 각각의 필드 값 검사
        if (error.hasErrors()) {// 에러값이 있으면
            System.out.println("에러");
            return "valid/insertForm";
        }
        // 객체 자체에 대해 검사- 문제가 있다면 글로벌 에러를 추가한다.
        if (command.getId() == 0) {
            // errorCode : 외부 properties 파일에 있는 코드로 에러 메세지를 가져온다.
            // defaultMessage : errorCdode에 해당되는 값이 없는 경우 대신 사용
            error.reject("nocode", "id값 오류");
            return "valid/insertForm";
        }
        return "valid/createDonePage";
    }

}
