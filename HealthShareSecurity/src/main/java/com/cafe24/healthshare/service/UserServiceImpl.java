package com.cafe24.healthshare.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.client.ClientProtocolException;
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
import com.cafe24.healthshare.util.KakaoLogin;
import com.cafe24.healthshare.vo.Auth;
import com.cafe24.healthshare.vo.OAuth;
import com.cafe24.healthshare.vo.User;
import com.cafe24.healthshare.vo.UserInfo;
import com.fasterxml.jackson.databind.JsonNode;

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
	public int joinUser(Join join){
		User user = new User();
		Auth auth = new Auth();
		OAuth oauth = new OAuth();
		UserInfo userinfo = new UserInfo();
		
		user.setUsername(join.getUsername());
		user.setPassword(encoder.encode(join.getPassword()));
		
		auth.setUsername(join.getUsername());
		// DB의 Auth Table의 Authority의 Default가 "ROLE_MEMBER" 이므로 set 생략
		
		
		  if(join.getOauth().equalsIgnoreCase("Y")) {
		  oauth.setUsername(join.getUsername()); oauth.setKakaoid(join.getUsername());
		  }
		 
		
		userinfo.setUsername(join.getUsername());
		userinfo.setNickname(join.getNickname());
		userinfo.setEmail(join.getMemail());
		try { userinfo.setIp(InetAddress.getLocalHost().getHostAddress()); } catch (UnknownHostException e1) { e1.printStackTrace(); }
		userinfo.setZonecode(join.getZonecode());
		userinfo.setAddress1(join.getAddress1());
		userinfo.setAddress2(join.getAddress2());
		userinfo.setInterest(join.getInterest());
		userinfo.setSns(join.getSns());
		
		int result =-1;
		try {
			result = mapper.insertUser(user);
			result = mapper.insertAuth(auth);
			result = mapper.insertUserInfo(userinfo);
			if(join.getOauth().equalsIgnoreCase("Y")) {result = mapper.insertOAuth(oauth);}
			if(result<=0) {throw new Exception("유저정보 생성 실패");}
		} catch (Exception e) {
			log.error("JOIN ERROR :\n"+e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return result;
	}

	@Override
	public UserInfo getUserInfo(String username) { return mapper.getUserInfo(username); }

	
	@Override
	public String getUsernameFromKakao(String code) {

		String kakaoId = "";
		User user = new User();
		try {
			JsonNode accessToken = KakaoLogin.getKakaoAccessToken(code);
			JsonNode userinfo = KakaoLogin.getKakaoUserInfo(accessToken);
			kakaoId = userinfo.get("id").asText();
			user = mapper.authenticate(kakaoId);
			if (user == null) { return kakaoId; }
		} 
		catch (ClientProtocolException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		
		return user.getUsername();
	}
	
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
