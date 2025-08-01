package com.julspringsecurity.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringsecurity.entity.Users;

public interface UsersRepository extends JpaRepository<Users,String>{
   //시큐리티에서는 form에 입력한 비번과 꺼내온 비번이 일치하는지 검사하는 기능을 내부적으로 가지고 있음
    Optional<Users> findByUsername(String username);

}
