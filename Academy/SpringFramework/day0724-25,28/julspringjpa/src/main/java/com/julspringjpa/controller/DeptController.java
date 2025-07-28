package com.julspringjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.julspringjpa.dao.DeptRepository;
import com.julspringjpa.dao.EmpRepository;
import com.julspringjpa.entity.Dept;
import com.julspringjpa.entity.Emp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class DeptController {

    @Autowired
    DeptRepository deptRepository;
    @Autowired
    EmpRepository empRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dept")
    public String deptAll(Model m) {
        List<Dept> dlist=deptRepository.findAll();
        m.addAttribute("dlist", dlist);

        return "dept/dept";
    }

    @GetMapping("/dept/form")
    public void form(){
        }
    
    @PostMapping("/dept/insert")
    public String insert(Dept dept) {
        //파라미터값 받아와서 DB에 insert
        deptRepository.save(dept);
        System.out.println("save 후");
        System.out.println(dept);

        return "redirect:/dept";
    }

    @GetMapping("/emp")
    public String empAll(Model m) {
        List<Emp> elist=empRepository.findAll();
        m.addAttribute("elist", elist);

        return "emp/emp";
    }
    

    @GetMapping("/emp/form")
    public void empForm(Model m) {
        List<Dept> dlist=deptRepository.findAll();
        m.addAttribute("dlist", dlist);
        
    }

    @PostMapping("/emp/insert")
    public String empInsert(Emp emp) {
        empRepository.save(emp);
        System.err.println(emp);

        return "redirect:/emp";

    }
    
}
