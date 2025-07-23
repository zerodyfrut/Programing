package com.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class Customer {
    private int custid;
    private String name;
    private String address;
    private String phone;

    private List<Book> books;

}
