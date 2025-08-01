package com.julspringsecurity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringsecurity.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByQuestion_qnoOrderByCreatedAt(Integer questionId);
}
