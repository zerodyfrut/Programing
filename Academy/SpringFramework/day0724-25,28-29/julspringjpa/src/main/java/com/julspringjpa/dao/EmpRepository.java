package com.julspringjpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringjpa.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp,Integer>{



    Page<Emp> findByHiredateBetween(Date start, Date end, Pageable paging);

    Page<Emp> findByEnameLike(String ename, Pageable paging);
} 
