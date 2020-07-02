/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io.entity;

import com.pgagtersales.pgaftersales.shared.dto.ClientDto;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "outstanding")
@Data
public class OutstandingEntity {
    public static final long serialVersionUID = 6411563565645445491L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

 /*   @Column(nullable = false, length = 50, unique = true, name = "client_id")
    private String  clientId;*/

    @Column(nullable = false, length = 50)
    private String description;

    @Column(nullable = false, length = 50, name = "invoiced_amount")
    private String invoicedAmount;

    @Column(nullable = false, name = "amount_paid")
    private String amountPaid;

    @Column(nullable = false, length = 50)
    private String user;

    @Column(nullable = false, length = 50, name = "last_f_date")
    private String lastFDate;

    @Column(nullable = false, name = "next_followup")
    private String nextFDate;

    @Column(nullable = false, name = "company_name")
    private String date;

    @Column(nullable = false, name = "is_active")
    private int visible;

   /* @ManyToOne
    @JoinColumn(name = "clients_id")
    private ClientsEntity clientDto;*/
}
