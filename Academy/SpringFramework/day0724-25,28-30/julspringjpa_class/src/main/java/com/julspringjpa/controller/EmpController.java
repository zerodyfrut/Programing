package com.julspringjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.julspringjpa.dao.EmpRepository;
import com.julspringjpa.service.EmpService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmpController {

    @Autowired
    EmpRepository empRepository;

    @Autowired
    EmpService empService;

    @GetMapping("/update/form/{empno}")
    public String getMethodName() {
        return "update/form";
    }

    @PatchMapping("/update/form/{empno}")
    @ResponseBody
    public String patchSal(@PathVariable("empno") int empno,
            @RequestParam(value = "newsal", defaultValue = "0") int newsal) {
        empService.changeSalary(empno, newsal);
        return "OK";
    }

    @DeleteMapping ("/delete")
    @ResponseBody
    public String deleteEmployees(@RequestBody List<Integer> empnos) {
        System.out.println(empnos);
        empService.deleteEmp(empnos);
        return "OK";
    }
}
