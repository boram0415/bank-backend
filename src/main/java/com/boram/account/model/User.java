package com.boram.account.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="users")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String userId;
    private String password;
    private String name;
    private LocalDateTime createdDt;

    @Builder
    public User(String userId,String password, String name){
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    @PrePersist
    public void prePersist(){
        this.createdDt = LocalDateTime.now();
    }

}
