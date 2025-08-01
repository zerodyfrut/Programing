package com.julspringsecurity.entity;

import jakarta.persistence.Column;
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
    // @Column(columnDefinition = "varchar(20)")
    private Role role;
    private char enabled;
}
