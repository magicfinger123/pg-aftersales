package com.pgagtersales.pgaftersales.io.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "users")
@Data
public class UserEntity implements Serializable {
    public static final long serialVersionUID = 2828520025360413233L;
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 50)
    private String first_name;

    @Column(nullable = false, length = 50)
    private String last_name;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 50)
    private String department;

    @Column(nullable = false, length = 50, unique = true)
    private String userId;

    @Column(nullable = false, length = 50)
    private String job_title;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 100)
    private String encryptedPassword;

    @Column(nullable = false, length = 50)
    private String role;

    @Column(nullable = false)
    private Boolean activated;
}
