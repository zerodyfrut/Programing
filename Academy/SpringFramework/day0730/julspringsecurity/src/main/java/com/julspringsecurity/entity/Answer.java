package com.julspringsecurity.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private int ano; // 글 번호
    private String title;//글 제목
    private String content; // 글 내용
    private Date create_at;    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qno")    
    private Question question;


}
