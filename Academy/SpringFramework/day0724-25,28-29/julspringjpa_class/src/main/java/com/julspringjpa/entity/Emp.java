package com.julspringjpa.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Emp {

    @Id
    private int empno;
    private String ename;
    private String job;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;
    private int sal;
	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="deptno")
    private Dept dept;


}
