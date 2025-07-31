package com.julspringsecurity.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.julspringsecurity.auth.SecurityUser;
import com.julspringsecurity.auth.UsersUserDetailsService;
import com.julspringsecurity.dao.AnswerRepository;
import com.julspringsecurity.dao.QuestionRepository;
import com.julspringsecurity.entity.Question;
import com.julspringsecurity.entity.Users;
import com.julspringsecurity.entity.Answer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api")
@Controller
public class QnaController {
    @Autowired
    QuestionRepository questionDao;

    @Autowired
    AnswerRepository answerDao;

    @GetMapping("/questions")
    public void questions(Model m) {
        List<Question> questions = questionDao.findAll();
        m.addAttribute("questions", questions);

    }

    @GetMapping("/questions/{id}")
    public String qnaIdList(Model m, @PathVariable("id") int qno, Question question, Answer answer) {

        question = questionDao.findById(qno).orElse(null);
        List<Answer> answers = answerDao.findByQuestion_Qno(qno);
        m.addAttribute("question", question);
        m.addAttribute("answers", answers);

        return "api/qnaIdList";
    }

    @GetMapping("/questionsForm")
    public void questionsForm() {
    }

    @PostMapping("/questions")
    public String insertQuestion(Question question, @AuthenticationPrincipal SecurityUser user) {

        Date date = new Date();

        question.setUsers(user.getUsers());
        question.setCreate_at(date);
        questionDao.save(question);

        return "redirect:/api/questions";
    }

    @GetMapping("/questions/update/{qno}")
    public String updateForm(@PathVariable("qno") int qno, Model m,
            @AuthenticationPrincipal SecurityUser user) {

        Optional<Question> optionalQuestion = questionDao.findById(qno);

        Question question = optionalQuestion.get();
        if (question.getUsers().getUsername().equals(user.getUsername())) {
            m.addAttribute("question", question);
            return "/api/questionUpdateForm";
        } else {
            return "redirect:/accessDenied";
        }

    }

    @PutMapping("/questions/update/{id}")
    public String updateQuestion(@PathVariable int id, Question newQuestion) {
        Question question = questionDao.findById(id).orElseThrow();
        question.setTitle(newQuestion.getTitle());
        question.setContent(newQuestion.getContent());

        questionDao.save(question);
        // 이러면 수정이 아니라 새로 만들어 지려나? ->qno 같은번호쓰게끔 -> 덮어쓰기

        // 작성자만 사용가능 -> 작성자만 update 버튼이 보이게끔 처리.
        return "redirect:/api/questions";
    }

    @DeleteMapping("questions/{id}")
    public String deleteQuestion(@PathVariable int id, @AuthenticationPrincipal SecurityUser user) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();

            // 로그인한 사용자의 username과 글 작성자의 username 비교
            if (question.getUsers().getUsername().equals(user.getUsername())) {
                questionDao.deleteById(id);
            } else {
                // 작성자가 아닌 경우 권한 없음 페이지로 리디렉션하거나 예외 처리
                return "redirect:/accessDenied";
            }
        }

        questionDao.deleteById(id);

        return "redirect:/api/questions";
    }

    @GetMapping("questions/{no}/answers")
    public String writeAnswer(@PathVariable("no") int questionId, Model m) {
        Question question = questionDao.findById(questionId).orElseThrow();
        m.addAttribute("question", question);
        return "api/answerForm";
    }

    @PostMapping("answers")
    public String insertAnswer(Answer answer, @AuthenticationPrincipal SecurityUser user) {
        Date date=new Date();

        answer.getQuestion().setUsers(user.getUsers());
        answer.setCreate_at(date);
        answerDao.save(answer);
        
        return "redirect:/api/questions/"+answer.getQuestion().getQno();
    }
    

    @PutMapping("answers/{id}")
    public String updateAnswer(@PathVariable int id, Answer answer) {


        answerDao.save(answer);
        return "redirect:/questions";
    }

    @DeleteMapping("answers/{id}")
    public String deleteAnswer(@PathVariable int id) {
        answerDao.deleteById(id);

        return "redirect:/questions";
    }
}
