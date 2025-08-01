package com.julspringsecurity.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringsecurity.entity.Users;

public interface UsersRepository extends JpaRepository<Users,String>{
    //시큐리티에서는 form에 입력한 password와 꺼내온 password가 
    //일치하는지 검사하는 기능을 내부적으로 가지고있다.
    
    Optional<Users> findByUsername(String username);
    //Optional 로 null값 발생하지 않도록.

    List findAll();
} 