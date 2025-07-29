package com.julspringjpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringjpa.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {
    // 첫자리 entity 타입, 두번째 : primary key 타입
    // JpaRepository로부터 물려받은 기본 메서드
    // 메서드 이름 패턴으로 작성된 메서드
    // 자동으로 생성하지 못하는 복잡한 SQL을 선언해서 사용한다.
}
//인터페이스는 다중 상속 가능하지 않나? -> 레포지토리를 하나로 작성가능? -> 가능할거같은데?
//엔티티는 추가될때마다 만들어 줘야하는데, 레포지토리는 계속 만들어야하나?
//->이름이 겹쳐서 따로 작성