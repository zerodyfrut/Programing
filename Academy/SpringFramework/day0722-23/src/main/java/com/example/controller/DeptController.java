package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.dao.DeptDao;
import com.example.dto.Dept;
import com.example.service.DeptService;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class DeptController {

    @Autowired
    DeptDao dao; // 구현 객체 주입

    @Autowired
    DeptService service;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
//gson을 이용한 부서출력
    // @GetMapping("/dept")
    // @ResponseBody
    // public String getMethodName() {
    //     Gson gson = new Gson();
    //     List<Dept> dlist = dao.deptAll();

    //     return gson.toJson(dlist); 
    // }

    @GetMapping("/dept")
    public String getMethodName(Model m) {
        //1. DB에서 모든 부서 번호를 가져와서
        List<Dept> dlist = dao.deptAll();
        //2. 모델에 추가
        m.addAttribute("dlist", dlist);
        //3. view에서 출력
        return "dept/list";
    }

    @GetMapping("/insert")
    public String form() {
        return "dept/insert";
    }

    @PostMapping("/insert")
    public String insert(Dept dept) {
        service.insert(dept);
        return "redirect:/dept";
    }

    @GetMapping("/update/{no}")
    public String updateform(@PathVariable("no")int deptno, Model m){
    //public String updateform(@PathVariable("no")int deptno,@PathVariable("loc")String loc, Model m){
        m.addAttribute("deptno", deptno);
        //m.addAttribute("loc",loc);

        return "dept/update";
    }

    @PutMapping("/update")
    public String update(@ModelAttribute("deptno") int deptno, @RequestParam("loc")String loc) {
        
        dao.update(deptno, loc);
        return "redirect:/dept";
    }

    @GetMapping("/delete/{no}")
    public String delete(@PathVariable("no") int deptno){
        System.out.println("deptno"+deptno);
        //delete
        dao.delete(deptno);
        return "redirect:/dept";
    }
}