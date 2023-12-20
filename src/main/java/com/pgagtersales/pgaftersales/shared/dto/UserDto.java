package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

import javax.annotation.Nullable;


@Data
public class UserDto {
    public static final long serialVersionUID = 7168715137979527464L;
    private String first_name;
    private String last_name;
    private String username;
    private String department;
    private String userId;
    private String job_title;
    private String password;
    private String encryptedPassword;
    private String role;
    private Boolean activated;
    @Nullable
    private String userPic;
    @Nullable
    private double ratings;
    @Nullable
    private int rating_count;
    @Nullable
    private String fileExt;
}
