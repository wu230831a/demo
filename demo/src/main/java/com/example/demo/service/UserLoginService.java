package com.example.demo.service;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.entity.UserLogin;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtil tokenUtil;

    public void createLogin(UserLogin userLogin){
        UserLogin dbLogin = userLoginRepository.findById(userLogin.getId()).get();
        if(dbLogin!=null){
            userLogin.setPwd(passwordEncoder.encode(userLogin.getPwd()));
            userLoginRepository.save(userLogin);
        }

        userLoginRepository.save(userLogin);
    }

    public UserLoginDto loginUser(UserLogin userLogin){
    		UserLogin dbUser = userLoginRepository.findById(userLogin.getId()).get();
    		if (userLogin.getPwd() != null && dbUser.getPwd() != null
    			     && userLogin.getPwd().equals(dbUser.getPwd())){
		    			String jwt = tokenUtil.createJwt(dbUser.getId());
		                UserLoginDto userDto = new UserLoginDto(dbUser.getId(),jwt);
		                return userDto;
    			     }
    		return null;
    }


}
