package com.julspringjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringjpa.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>{
    //JpaRepository로 부터 물려받은 기본 메서드.
    //메서드 이름 패턴으로 작성된 메서드
    //자동으로 생성하지 못하는 복잡한 SQL만 선언 해서 사용한다.
}
