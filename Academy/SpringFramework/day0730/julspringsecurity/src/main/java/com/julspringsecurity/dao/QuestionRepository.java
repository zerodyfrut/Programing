package com.julspringsecurity.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringsecurity.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {



}
