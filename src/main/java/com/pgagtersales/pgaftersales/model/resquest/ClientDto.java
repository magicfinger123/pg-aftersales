/*
 * Copyright (c) 2020. Magic-finger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.model.resquest;

import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import lombok.Data;

import java.util.List;

@Data
public class ClientDto {

    private String  username;
    private String first_name;
    private String last_name;
    private String title;
    private String phone;
    private String email;
    private String company;
    private String address;
    private String cluster;
    private int visible;
    private String password;
    private List<GeneratorDto> generatorDtos;
}
