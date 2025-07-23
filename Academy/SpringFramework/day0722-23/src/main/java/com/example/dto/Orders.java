package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Orders {
    private int orderid;
    private int custid;
    private int bookid;
    private int saleprice;
    private Date orderdate;
}
