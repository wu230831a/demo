package com.example.demo.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "form_info")
public class FormInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(name = "deadline",columnDefinition = "TIMESTAMP")
    private Date deadline;
    
    @Column(name="apply_user")
    private String applyUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_user",insertable = false, updatable = false)
    private UserInfo applyUser;

    @Column(name="reviewer")
    private String reviewerId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer",insertable = false, updatable = false)
    private UserInfo reviewer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.ALL)
    private List<FormList> formList;
    
    @Column(name="status_id")
    private int statusId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private FormStatus formStatus;
    
    public FormInfo(String title,String description,String applyUserId,String reviewerId) {
    	this.title = title;
    	this.description = description;
    	this.applyUserId = applyUserId;
    	this.reviewerId = reviewerId;
    	this.deadline = new Date();
    	this.statusId = 1;
    }
}
