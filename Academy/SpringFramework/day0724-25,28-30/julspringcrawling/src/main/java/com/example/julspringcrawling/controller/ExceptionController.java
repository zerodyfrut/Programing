package com.example.julspringcrawling.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.julspringcrawling.exception.BoardNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExceptionController {
    // @RequestMapping("/board")
    @GetMapping("/boardError")
    public String boardError() {
        throw new BoardNotFoundException("해당 게시글은 존재하지 않습니다.");
    }

    // @RequestMapping("/illegalArgumentError")
    @GetMapping("/illegalArgumentError")
    public String illegalArgumentError() {
        throw new IllegalArgumentException("부적절한 인자가 전달되었습니다.");
    }

    //@RequestMapping("/sqlError")
    @GetMapping("/sqlError")
    public String sqlError() throws SQLException {
        throw new SQLException("SQL 구문에 오류가 있습니다.");
    }
    
    // 특정 예외만 다르게 처리하고 싶을 때, controller에 handler 처리
    @ExceptionHandler(SQLException.class)
    public String sqpProblem(SQLException e, Model m) {
        m.addAttribute("exception", e);
        return "/errors/sqlError";
    }

}
