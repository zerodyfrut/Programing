package com.example.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.command.SearchCommand;
import com.example.command.SearchType;


@Controller
@RequestMapping("/search")
public class SearchController {

    @ModelAttribute("searchTypeList")
    public List<SearchType> getSearchTypeList() {
        List<SearchType> options = new ArrayList<>();

        options.add(new SearchType(1, "전체"));
        options.add(new SearchType(2, "뉴스"));
        options.add(new SearchType(3, "Post"));

        return options;
    }

    @ModelAttribute("popularQueryList")
    public String[] getpopularQueryList() {

        return new String[] { "Python", "AI", "Spring" };
    }

    @GetMapping("/main")
    public void main() {

    }

    @GetMapping("/result")
    public void result(@ModelAttribute("search") SearchCommand searchCommand, Model m) {
        // 검색어를 사용해 DB Select 했다고 가정,
        // DS에서 만들지 않은 자료라서 DS에 저장안되있음.
        // Model을 통해서 view에 전달해야함.

        m.addAttribute("result1", "검색 결과물1");
        m.addAttribute("result2", "검색 결과물2");



    }

}
