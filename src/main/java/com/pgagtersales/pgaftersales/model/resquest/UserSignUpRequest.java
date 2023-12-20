package com.pgagtersales.pgaftersales.model.resquest;

import lombok.Data;

import javax.annotation.Nullable;

@Data
public class UserSignUpRequest {
    private String first_name;
    private String last_name;
    private String username;
    private String department;
    private String job_title;
    private String password;
    private String role;
    @Nullable
    private String userPic;
    @Nullable
    private double ratings = 5;
    @Nullable
    private int rating_count = 1;
    @Nullable
    private String fileExt;

}
