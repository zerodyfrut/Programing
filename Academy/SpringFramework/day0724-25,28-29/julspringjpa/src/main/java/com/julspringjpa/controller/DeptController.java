package com.julspringjpa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.julspringjpa.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.julspringjpa.dao.DeptRepository;
import com.julspringjpa.dao.EmpRepository;
import com.julspringjpa.entity.Dept;
import com.julspringjpa.entity.Emp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeptController {

    private final EmpService empService;

    @Autowired
    DeptRepository deptRepository;
    @Autowired
    EmpRepository empRepository;
    @Autowired
    EmpService service;

    DeptController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/dept")
    public String deptAll(Model m) {
        List<Dept> dlist = deptRepository.findAll();
        m.addAttribute("dlist", dlist);

        return "dept/dept";
    }

    @GetMapping("/dept/form")
    public void form() {
    }

    @PostMapping("/dept/insert")
    public String insert(Dept dept) {
        // 파라미터값 받아와서 DB에 insert
        deptRepository.save(dept);
        System.out.println("save 후");
        System.out.println(dept);

        return "redirect:/dept";
    }

    @GetMapping("/emp/form")
    public void empForm(Model m) {
        List<Dept> dlist = deptRepository.findAll();
        m.addAttribute("dlist", dlist);

    }

    @PostMapping("/emp/insert")
    public void empInsert(Emp emp) {
        empRepository.save(emp);
        System.err.println(emp);

    }

    @GetMapping("/emp/emps")
    public void empAll(@RequestParam(value = "p", defaultValue = "0") int p, Model m) {
        // // 전체 사원정보 가져오기
        // List<Emp> elist=empRepository.findAll();
        // m.addAttribute("elist", elist);

        // 요청 page에 맞는 데이터만 가져오기
        Pageable paging = PageRequest.of(p, 5);
        Page<Emp> page = empRepository.findAll(paging);

        m.addAttribute("page", page);

    }

    @GetMapping("/emp/searchEmp")
    public void researchEmp() {
    }

    @GetMapping("/emp/resultEmp")
    public void researchEmp(@RequestParam(value = "p", defaultValue = "0") int p,
            @ModelAttribute(value = "ename") String ename, Model m) {
        // List<Emp> elist = empRepository.findByEnameLike("%" + ename + "%");

        // if (elist == null) {
        // elist = new ArrayList<>();
        // }

        // m.addAttribute("elist", elist);

        Pageable paging = PageRequest.of(p, 5);
        Page<Emp> page = empRepository.findByEnameLike("%" + ename + "%", paging);

        m.addAttribute("page", page);
    }

    @GetMapping("/emp/searchDate")
    public void researchDate() {
    }

    @GetMapping("/emp/resultDate")
    public void researchDate(@RequestParam(value = "p", defaultValue = "0") int p,
            @ModelAttribute(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @ModelAttribute(value = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, Model m) {
        // List<Emp> elist = empRepository.findByHiredateBetween(start, end);

        // if (elist == null) {
        // elist = new ArrayList<>();
        // }

        // m.addAttribute("elist", elist);

        Pageable paging = PageRequest.of(p, 5);
        Page<Emp> page = empRepository.findByHiredateBetween(start, end, paging);

        m.addAttribute("page", page);
        m.addAttribute("start", start);
        m.addAttribute("end", end);
    }

    // ----------------------------------

    @GetMapping("/search/form")
    public void search1Form() {
    }

    @GetMapping("/result1")
    public String result1(@ModelAttribute("search") String name, @RequestParam(value = "p", defaultValue = "0") int p,
            Model m) {
        Pageable paging = PageRequest.of(p, 5);
        Page<Emp> page = empRepository.findByEnameLike("%" + name + "%", paging);
        m.addAttribute("page", page);
        return "search/emps1";
    }

    @GetMapping("/result2")
    public String result2(@ModelAttribute("begin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date begin,
            @ModelAttribute("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
            @RequestParam(value = "p", defaultValue = "0") int p, Model m) {
        Pageable paging = PageRequest.of(p, 5);

        Page<Emp> page = empRepository.findByHiredateBetween(begin, end, paging);
        m.addAttribute("page", page);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        m.addAttribute("strbegin", sdf.format(begin));
        m.addAttribute("strend", sdf.format(end));

        return "search/emps2";

    }

    @GetMapping("/update/form/{empno}")
    public String updateForm() {
        return "update/form";
    }

    @PatchMapping("/update/form/{empno}")
    public String patchSal(@PathVariable("empno") int empno,
            @RequestParam(value = "newsal", defaultValue = "0") int newsal) {

        service.changeSal(empno, newsal);

        return "redirect:/";
    }

    @GetMapping("/delete/form/{empno}")
    public String getMethodName(@PathVariable("empno") int empno) {
        service.delete(empno);

        return "redirect:/";

    }

}
