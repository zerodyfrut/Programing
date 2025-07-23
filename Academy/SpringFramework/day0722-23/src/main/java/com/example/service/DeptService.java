package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.DeptDao;
import com.example.dto.Dept;

@Service
public class DeptService {

    @Autowired
    DeptDao dao;

    public int insert(Dept dept) {
        // 1. 새로운 부서 번호 가져옴
        int nwNo = dao.getNwno();
        // 2. dept에 저장
        dept.setDeptno(nwNo);
        // 3. dept 에 insert 수행
        return dao.insert(dept);

    }

}
