package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@javax.persistence.Entity()
@javax.persistence.Table(name = "users")
@org.hibernate.annotations.Entity()
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public User (String login, String password) {
        this.login = login;
        this.password = password;
    }
}
