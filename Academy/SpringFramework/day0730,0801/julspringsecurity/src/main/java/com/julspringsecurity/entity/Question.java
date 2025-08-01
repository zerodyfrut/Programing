package com.julspringsecurity.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"answers", "users"}) // ❗ 순환 참조 방지
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private int qno; // 글 번호
    private String title;//글 제목
    private String content; // 글 내용
    private Date create_at;
    //private LocalDateTime createdAt = LocalDateTime.now();

    private Date update_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="username")    
    private Users users;

    
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Answer> answers;

}
