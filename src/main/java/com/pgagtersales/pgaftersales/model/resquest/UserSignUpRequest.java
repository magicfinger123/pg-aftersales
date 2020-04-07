package com.pgagtersales.pgaftersales.model.resquest;

import lombok.Data;

@Data
public class UserSignUpRequest {
    private String first_name;
    private String last_name;
    private String username;
    private String department;
    private String job_title;
    private String password;
    private String role;
}
