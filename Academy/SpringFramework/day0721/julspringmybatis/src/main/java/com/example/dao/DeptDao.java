package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.Dept;

@Mapper
public interface DeptDao {
    @Select("select * from dept")
    List<Dept> deptAll();

}
