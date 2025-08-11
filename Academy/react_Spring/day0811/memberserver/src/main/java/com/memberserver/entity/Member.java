package com.memberserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //기본적인 lombok
@NoArgsConstructor // 아무것도 없는 기본 생성자
@Entity //엔티티 선언
@Table(name="member") //테이블 이름 선언(파일명과 테이블명이 다를때)
public class Member {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //(자동증가)
    @Column(name = "id_no")
    private Long idNo; // id고유번호
    
    private String id; // id
    private String name; // 이름
    private String image; //이미지의 경로
    private String phone; // 전화번호
    private String address; // 주소
    private String role; // 등급
}
