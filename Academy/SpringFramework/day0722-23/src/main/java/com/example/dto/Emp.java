package com.example.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Emp {//자식
    private int empno;
    private String ename;
    private String job;
    private int mgr;
    private Date hiredate;
    private double sal;
    private double comm;
    private int depno;

    private Dept dept; //부모
}
