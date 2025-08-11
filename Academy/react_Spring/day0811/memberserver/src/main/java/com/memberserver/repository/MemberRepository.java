package com.memberserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.memberserver.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{

    
} 
