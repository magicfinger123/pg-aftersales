package com.pgagtersales.pgaftersales.io.entity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "clients")
@Data
public class ClientsEntity {
    public static final long serialVersionUID = 6411563565645445491L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50, unique = true, name = "username")
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private List<OutstandingEntity> outstandingDtos = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private List<GeneratorEntity> generatorDtos = new ArrayList<>();
}

