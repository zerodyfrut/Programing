package com.julspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.julspringsecurity.dao.QuestionRepository;
import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.entity.Question;
import com.julspringsecurity.entity.Users;

import jakarta.transaction.Transactional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepo;
    @Autowired
    UsersRepository userRepo;

    public Page<Question> findAll(int p) {
        Pageable paging = PageRequest.of(p, 5);
        return questionRepo.findAll(paging);
    }

    public Question create(Question question, String username) {
        Users user = userRepo.findByUsername(username).orElseThrow();
        question.setAuthor(user);
        questionRepo.save(question);
        return question;
    }

     public Question findOne(int qno) {
        return questionRepo.findById(qno).orElse(new Question());
    }

    @Transactional
    public void update(Question question ) {
        Question q = findOne(question.getQno()); //기존글
        q.setTitle(question.getTitle());//변경사항 -title
        q.setContent(question.getContent());// 변경사항-content
    }

    public void delete(int qno) {
        questionRepo.deleteById(qno);
    }
}