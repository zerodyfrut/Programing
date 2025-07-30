package com.julspringjpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Dept {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // auto increment
    private int deptno;
    @Column(columnDefinition = "TEXT") //text와 varchar 차이
    // 특정 컬럼의 타입을 지정
    private String dname;
    private String loc;

    // @OneToMany(mappedBy = "dept",cascade= CascadeType.REMOVE,fetch = FetchType.EAGER)
    // private List<Emp> empList=new ArrayList<>();

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Emp> elist;
    
}
