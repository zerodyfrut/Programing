package com.julspringsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.julspringsecurity.auth.SecurityUser;
import com.julspringsecurity.dao.AnswerRepository;
import com.julspringsecurity.dao.QuestionRepository;
import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.dto.AnswerRequest;
import com.julspringsecurity.entity.Answer;
import com.julspringsecurity.entity.Question;
import com.julspringsecurity.entity.Users;
import com.julspringsecurity.service.AnswerService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class AnswerController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepo;
    
    @Autowired
    AnswerService answerService;

    //답변글 추가
    @PostMapping("/api/answers")
    public List<Answer> insert(@RequestBody AnswerRequest answerRequest, @AuthenticationPrincipal SecurityUser users, Answer answer) {

        Users author = usersRepository.findById(users.getUsername()).get();
        Question question = questionRepository.findById(answerRequest.getQuestionId()).get();
        // System.out.println("qno : "+qno);
        answer.setAuthor(author);
        answer.setQuestion(question);
        answer.setContent(answerRequest.getContent());
       
        answerRepo.save(answer);    
       
        List<Answer> answerList = answerRepo.findByQuestion_qnoOrderByCreatedAt(answerRequest.getQuestionId());

        return answerList;
    }
    
    //수정
    @PutMapping("/api/answers")
    public Answer update(@RequestParam int answerid, @RequestParam String content) {
    
        Answer answer = answerService.updatAnswer(answerid, content);

        return answer;
    }

    
    //삭제
    @DeleteMapping("/api/answers")
    public int delete(@RequestParam int answerid){
        answerRepo.deleteById(answerid);
        return answerid;
    }
}
