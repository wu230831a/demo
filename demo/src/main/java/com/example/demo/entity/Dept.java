package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dept")
public class Dept {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "dept_name")
    private String name;
}
