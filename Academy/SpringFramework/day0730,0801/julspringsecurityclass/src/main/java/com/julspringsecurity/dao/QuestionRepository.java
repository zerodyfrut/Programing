package com.julspringsecurity.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.julspringsecurity.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAllByOrderByCreatedAtDesc(Pageable pageing);

}
