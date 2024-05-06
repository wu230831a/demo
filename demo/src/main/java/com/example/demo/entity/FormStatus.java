package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "form_status")
public class FormStatus {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "status_name")
    private String name;
}
