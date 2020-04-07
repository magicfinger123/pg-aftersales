package com.pgagtersales.pgaftersales.model.response;


import lombok.Data;

@Data
public class UserRest {
    private String first_name;
    private String last_name;
    private String username;
    private String department;
    private String userId;
    private String job_title;
    private String role;
    private Boolean activated;
}
