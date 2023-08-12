package com.pgagtersales.pgaftersales.io.entity;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user")
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

    @Column(name = "image")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String userPic;

    @Column(nullable = true)
    private double ratings;

    @Column(nullable = true)
    private int rating_count;

    @Column(nullable = false, name = "active")
    private Boolean activated = true;

   // Storage storage = StorageOptions.getDefaultInstance().getService();
}
