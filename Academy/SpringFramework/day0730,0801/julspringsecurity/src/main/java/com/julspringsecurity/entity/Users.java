package com.julspringsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Users {
    @Id
    private String username;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    //@Colume(columnDefinition="varchar(20)") //타입을 지정할 때,
    private Role role; // Enum(String타입)으로 저장
    private char enabled;
}
