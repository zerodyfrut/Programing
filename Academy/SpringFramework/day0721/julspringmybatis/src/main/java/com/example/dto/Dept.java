package com.example.dto;

import lombok.Data;

@Data
public class Dept {
    private int deptno;// 컬럼명==변수명
    private String dname;
    private String loc;
}
