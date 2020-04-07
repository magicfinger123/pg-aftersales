package com.pgagtersales.pgaftersales.io.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "clients")
@Data
public class ClientsEntity {
    public static final long serialVersionUID = 6411563565645445491L;
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 50, unique = true)
   private String  username;

    @Column(nullable = false, length = 50)
   private String password;

    @Column(nullable = false, length = 50)
   private String first_name;

    @Column(nullable = false)
   private String last_name;

    @Column(nullable = false, length = 50)
   private String title;

    @Column(nullable = false, length = 50)
   private String phone;

    @Column(nullable = false)
   private String email;

    @Column(nullable = false, name = "company_name")
   private String company;

    @Column(nullable = false)
    @Type(type = "text")
   private String address;

    @Column(nullable = false, length = 50)
   private String cluster;

    @Column(nullable = false)
   private int visible;
}
