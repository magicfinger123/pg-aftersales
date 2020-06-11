/*
 * Copyright (c) 2020. Magic-finger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;

@Data
public class ClientDto {
    private int  id;
    private String  username;
    private String first_name;
    private String last_name;
    private String title;
    private String phone;
    private String email;
    private String company;
    private String address;
    private String cluster;
    private String visible;
}
