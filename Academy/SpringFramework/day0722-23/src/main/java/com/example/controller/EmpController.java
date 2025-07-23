package com.example.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.dao.EmpDao;
import com.example.dto.Dept;
import com.example.dto.Emp;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class EmpController {

    @Autowired
    EmpDao dao;

    @GetMapping("name/search")
    public String form() {
        return "emp/search";
    }

    @GetMapping("name/result1")
    public String result(@ModelAttribute("name") String name, Model m) {

        List<Emp> elist = dao.searchName(name);
        m.addAttribute("elist", elist);
        m.addAttribute("size", elist.size());

        return "emp/result";
    }

    // @GetMapping("name/search2")
    // public String dept(Model d) {
    //     List<Dept> dlist = dao.depts();
    //     d.addAttribute("dlist", dlist);
    //     return "emp/search2";
    // }

    // @PostMapping("name/search2")
    // public String emp(Model e, @ModelAttribute("deptno") int deptno) {
    //     List<Dept> elist = dao.emps(deptno);
    //     e.addAttribute("elist", elist);
    //     return "emp/search2";
    // }

    // @PutMapping("name/search2")
    // public String empOne(Model o, @ModelAttribute("empno") int empno) {
    //     Emp emp = dao.empOne(empno);
    //     o.addAttribute("emp", emp);
    //     return "emp/search2";
    // }

    @GetMapping("/select/dept")
    public String depts(Model m) {
        List<Dept> dlist=dao.depts();

        m.addAttribute("dlist", dlist);
        return "emp/search2";
    }

    @GetMapping("/select/emps/{deptno}")
    @ResponseBody
    //return 한 JSON을 바로 출력하게끔
    public String emps(@PathVariable("deptno") int deptno){
        List<Map<String,Object>> elist=dao.emps(deptno);
        //ename, empno 저장을 위해 Object 타입으로
        Gson gson=new Gson();

        return gson.toJson(elist); //javaObject -> JSON
    }

    @GetMapping("/select/emp/{empno}")
    @ResponseBody
    public String empOne(@PathVariable("empno") int empno){
        Emp emp=dao.empOne(empno);
        Gson gson=new Gson();
        //Bean으로 만들어서 써도된다는데 어떻게하나

        return gson.toJson(emp);
        //{변수명:값,변수명:값...}
    }

}