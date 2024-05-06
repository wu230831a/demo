package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "id")
    private String id;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "id",insertable = false, updatable = false)
    // private EmpLogin empLogin;

    @Column(name="dept_id")
    private String deptId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", insertable = false, updatable = false)
    private Dept dept;
    
    @Column(name="position_id")
    private String positionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    private Position position;

    @Column(name = "username")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "admin_id")
    private String adminId;
}
