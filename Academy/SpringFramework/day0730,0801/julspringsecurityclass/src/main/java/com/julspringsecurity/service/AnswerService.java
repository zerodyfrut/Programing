package com.julspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julspringsecurity.dao.AnswerRepository;
import com.julspringsecurity.entity.Answer;

import jakarta.transaction.Transactional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepo;

    @Transactional
    public Answer updatAnswer(int answerid, String content){
        Answer answer = answerRepo.findById(answerid).get();
        answer.setContent(content);
        return answer;
    }

}
