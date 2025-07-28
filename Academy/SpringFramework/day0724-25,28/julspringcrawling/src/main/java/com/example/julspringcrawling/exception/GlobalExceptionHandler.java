package com.example.julspringcrawling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardException.class)
    public String handleCustomException(BoardException e, Model m) {
        m.addAttribute("exception", e);
        return "errors/boardError"; // 예외발생시 보여질 view 경로
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model m) {
        //System.out.println("Global handler 호출됨: " + e.getClass());

        m.addAttribute("exception", e);
        return "errors/globalError";
    }
}
