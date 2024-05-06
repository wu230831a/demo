package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserLogin;
import com.example.demo.repository.UserLoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserLoginRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(final String username) {
        final UserLogin user = userRepository.findById(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.getId(),user.getPwd(),user.getUserInfo().getPositionId(),user.getUserInfo().getDeptId());
    }
}

