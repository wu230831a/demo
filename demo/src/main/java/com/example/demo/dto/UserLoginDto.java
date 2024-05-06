package com.example.demo.dto;

import com.example.demo.entity.Dept;
import com.example.demo.entity.Position;

import lombok.Data;

@Data
public class UserLoginDto {

    private String id;
    private String name;
    private Dept dept;
    private Position position;
    private String token;

    public UserLoginDto(String id,String token){
        this.id = id;
        this.token = token;
    }
    public UserLoginDto(String id){
        this.id = id;
    }
}
