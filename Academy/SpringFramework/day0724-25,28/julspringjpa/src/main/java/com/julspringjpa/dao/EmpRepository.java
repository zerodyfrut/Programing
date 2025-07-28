package com.julspringjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringjpa.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp,Integer>{
} 
