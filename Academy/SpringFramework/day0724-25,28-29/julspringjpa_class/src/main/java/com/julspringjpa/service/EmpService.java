package com.julspringjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.julspringjpa.dao.EmpRepository;
import com.julspringjpa.entity.Emp;

@Service
public class EmpService {

    @Autowired
    EmpRepository empRepository;

    @Transactional
    public void changeSalary(int empno, int newSal) {
        Emp emp = empRepository.findById(empno).orElseThrow();
        emp.setSal(newSal); // 변경 감지 (dirty checking)
        // JPA는 트랜잭션 내에서 엔티티의 필드가 변경되면 자동으로 UPDATE 쿼리를 생성함.
    }
    @Transactional  
    public void deleteEmp(List<Integer> empnos){
        empRepository.deleteAllByEmpnoIn(empnos);
    }

}
