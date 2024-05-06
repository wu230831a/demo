package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.UserLogin;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String position;
    private String department;

    public CustomUserDetails(String username, String password, String position, String department) {
        this.username = username;
        this.password = password;
        this.position = position;
        this.department = department;
    }
    
    public CustomUserDetails(UserLogin userLogin) {
        this.username = userLogin.getUserInfo().getName();
        this.password = userLogin.getPwd();
        this.position = userLogin.getUserInfo().getPosition().getName();
        this.department = userLogin.getUserInfo().getDept().getName();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
    
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}