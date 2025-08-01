package com.julspringsecurity.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Answer {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ano;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Question question;

    private LocalDateTime createdAt = LocalDateTime.now();
}

