/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.shared.dto;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;

@Data
public class EmailsDto {
    private String type;
    private String emailAddress;
}
