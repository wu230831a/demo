package com.example.demo.service;

import javax.transaction.Transactional;

import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserLogin;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserLoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;

 @Service
 public class UserInfoService {
 private UserInfoRepository userInfoRepository;
 private PasswordEncoder passwordEncoder;
 private UserLoginRepository userLoginRepository;

  @Autowired
  public UserInfoService(UserInfoRepository userInfoRepository,
                         PasswordEncoder passwordEncoder,UserLoginRepository userLoginRepository) {
  this.userInfoRepository = userInfoRepository;
  this.passwordEncoder = passwordEncoder;
  this.userLoginRepository = userLoginRepository;
  }
 
 @Transactional
 public boolean createEmp(UserInfo userInfo,String pwd) {
	 UserInfo dbUser = userInfoRepository.findById(userInfo.getId()).get();
	 if ( dbUser == null) {
		 UserInfo newUser = userInfoRepository.save(dbUser);
		 UserLogin newLogin = new UserLogin();
		 newLogin.setId(newUser.getId());
		 newLogin.setPwd(passwordEncoder.encode(pwd));
		 userLoginRepository.save(newLogin);
		 return true;
	 }
	 return false;
 }

 @Transactional
 public Page<UserInfo> getUser(String id,String name,String deptId,Integer positionId, Integer pageNumber){
	 Pageable pageable = PageRequest.of(pageNumber, 10);
	 return userInfoRepository.findUser(id,name,deptId,positionId,pageable);
 }
 
 @Transactional
 public UserInfo updateUser(UserInfo userInfo) {
	 UserInfo dbUser = userInfoRepository.findById(userInfo.getId()).get();
	 if(dbUser==null)
		 return null;
	 if (userInfo.getName() != null)
		 dbUser.setName(userInfo.getName());
	 if (userInfo.getDept() != null)
		 dbUser.setDept(userInfo.getDept());
	 if (userInfo.getPosition() != null)
		 dbUser.setPosition(userInfo.getPosition());
	 if (userInfo.getTel() != null)
		 dbUser.setTel(userInfo.getTel());
	 if (userInfo.getAdminId() != null)
		 dbUser.setAdminId(userInfo.getAdminId());
	 UserInfo newUser = userInfoRepository.save(dbUser);
	 return newUser;
 }
 
 @Transactional
 public boolean deleteUser(String id) {
	 UserLogin dbUser = userLoginRepository.findById(id).get();
	 if(dbUser==null)
		 return false;
	 dbUser.setEnabled(false);
	 userLoginRepository.save(dbUser);
	 return true;
	 
 }
}
