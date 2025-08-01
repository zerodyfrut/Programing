package com.julspringsecurity.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity

public class Question {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qno;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users author;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "question",  fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference  //@JsonManagedReference → 직렬화 대상 
                           //@JsonBackReference → 직렬화에서 제외 (역참조 차단)
    private List<Answer> answerList;
}
