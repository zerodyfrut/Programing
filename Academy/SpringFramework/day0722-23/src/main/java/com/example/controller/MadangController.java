package com.example.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.MadangDao;
import com.example.dto.Book;
import com.example.dto.Customer;
import com.example.dto.Emp;
import com.google.gson.Gson;

@Controller
public class MadangController {

    @Autowired
    MadangDao dao;

    @GetMapping("/madang")
    public String print(Model b, Model c) {
        List<Book> books = dao.allBook();
        b.addAttribute("books", books);
        List<Customer> customers = dao.allCustomer();
        c.addAttribute("customers", customers);

        return "madang/allInfo";
    }
    

    @GetMapping("/madang/{custid}")
    public String detail(@PathVariable("custid") int custid, Model m) {
        Map<String,Object>sum=dao.sum(custid);

        List<Map<String,Object>> orders=dao.orders(custid);


        m.addAttribute("sum", sum);
        m.addAttribute("orders", orders);

        

        return "madang/result_my";
    }

}
