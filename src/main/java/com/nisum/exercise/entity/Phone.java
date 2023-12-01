package com.nisum.exercise.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    private Long phoneId;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @Column(name = "contry_code", nullable = false)
    private String contryCode;

    @Column(name = "usuario_id")
    private Long usuarioId;

}
