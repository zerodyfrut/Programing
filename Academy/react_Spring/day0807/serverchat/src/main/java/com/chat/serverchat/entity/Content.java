// package com.chat.serverchat.entity;

// import java.time.LocalTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.Data;

// @Entity
// @Data
// public class Content {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer contentid;

//     private String content;
//     private LocalTime timestamp;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name="userid")
//     private Sender sender;

// }
