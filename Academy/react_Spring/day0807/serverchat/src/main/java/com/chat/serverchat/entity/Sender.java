// package com.chat.serverchat.entity;

// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import lombok.Data;


// @Data
// @Entity
// public class Sender {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer userid; // 유저 고유번호

//     private String sender; // 이름
//     private String connected; // 접속여부 확인

//     // private List<String> content;
//     // private LocalDateTime timestamp; 
    
//     //해당 유저가 했던 채팅, 시간 -> 따로 entity 만들어야할 듯.
//     //유저가했던 채팅(content) 가 유저에 속하도록 ->cf. day 0722

//     @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
//     private List<Content> contents;

// }
