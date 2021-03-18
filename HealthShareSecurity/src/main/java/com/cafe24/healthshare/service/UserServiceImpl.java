package com.cafe24.healthshare.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cafe24.healthshare.dto.Join;
import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.dto.UpdatePass;
import com.cafe24.healthshare.mapper.UserMapper;
import com.cafe24.healthshare.vo.Auth;
import com.cafe24.healthshare.vo.User;
import com.cafe24.healthshare.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;	
	PasswordEncoder encoder;
	
	public UserServiceImpl() { encoder = new BCryptPasswordEncoder(); }
	
	@Override
	public int validateUser(String username) { return mapper.validateUser(username); }

	@Override
	@Transactional
	public int joinUser(Join join) throws UnknownHostException {
		User user = new User();
		Auth auth = new Auth();
		UserInfo userinfo = new UserInfo();
		
		user.setUsername(join.getUsername());
		user.setPassword(encoder.encode(join.getPassword()));
		
		auth.setUsername(join.getUsername());
		// DB의 Auth Table의 Authority의 Default가 "ROLE_MEMBER" 이므로 set 생략
		
		userinfo.setUsername(join.getUsername());
		userinfo.setNickname(join.getNickname());
		userinfo.setEmail(join.getMemail());
		userinfo.setIp(InetAddress.getLocalHost().getHostAddress());
		userinfo.setZonecode(join.getZonecode());
		userinfo.setAddress1(join.getAddress1());
		userinfo.setAddress2(join.getAddress2());
		userinfo.setInterest(join.getInterest());
		userinfo.setSns(join.getSns());
		
		int result =-1;
		try {
			result = mapper.insertUser(user);
			if(result>0) {result = mapper.insertAuth(auth);} else {throw new Exception("유저생성실패");}
			if(result>0) {result = mapper.insertUserInfo(userinfo);} else {throw new Exception("권한생성실패");}
			if(result<=0) {throw new Exception("유저정보 입력 실패");}
		} catch (Exception e) {
			log.error("JoinAction :"+e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Override
	public UserInfo getUserInfo(String username) { return mapper.getUserInfo(username); }

	@Override
	public int updateUserInfo(UpdateInfo info) { return mapper.updateUserInfo(info); }

	@Override
	public int updateUserPass(UpdatePass updatepass) {
		int result=-1;
		User user = new User();
		String pass = mapper.authenticate(updatepass.getUsername()).getPassword();
		if(encoder.matches(updatepass.getOld(),pass)){
			user.setUsername(updatepass.getUsername());
			String encodedPass =encoder.encode(updatepass.getNew_());  
			user.setPassword(encodedPass);
			result = mapper.updatePassword(user);
		}
		return result;
	}

	@Override
	public int deleteUser(User user) {
		int result = -1;
		if(encoder.matches(user.getPassword(), mapper.authenticate(user.getUsername()).getPassword())) {
			result = mapper.deleteUser(user);
		}
		return result; 
	}

}
