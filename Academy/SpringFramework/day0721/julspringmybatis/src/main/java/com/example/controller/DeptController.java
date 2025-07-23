package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.DeptDao;
import com.example.dto.Dept;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class DeptController {

    @Autowired
    DeptDao dao; // 구현 객체 주입

    @GetMapping("dept")
    public String getMethodName() {
        Gson gson = new Gson();
        List<Dept> dlist = dao.deptAll();

        return gson.toJson(dlist);
    }

}
