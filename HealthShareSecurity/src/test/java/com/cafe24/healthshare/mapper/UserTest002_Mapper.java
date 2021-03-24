package com.cafe24.healthshare.mapper;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.vo.Auth;
import com.cafe24.healthshare.vo.OAuth;
import com.cafe24.healthshare.vo.User;
import com.cafe24.healthshare.vo.UserInfo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
@Slf4j
public class UserTest002_Mapper {

	PasswordEncoder pwencoder;
	
	@Autowired
	private UserMapper mapper;

	
	public void insertUser() { 
		pwencoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setUsername("user1");
		String pass = pwencoder.encode("1111");
		log.info("PASS : "+pass);
		user.setPassword(pass);
		log.info(".........insertUser..........."+mapper.insertUser(user));
	}
	
	public void insertAuth() {
		Auth auth = new Auth();
		auth.setUsername("user1");
		auth.setAuthority("ROLE_MEMEBER");
		log.info("..........insertAuth............"+mapper.insertAuth(auth));
	}
	
	public void insertUserInfo() throws UnknownHostException {
		UserInfo info = new UserInfo();
		info.setUsername("user1");
		info.setEmail("aaa@a.com");
		info.setIp(InetAddress.getLocalHost().getHostAddress());
		info.setInterest("SQL");
		info.setSns("Y");
		info.setZonecode(123456);
		info.setAddress1("주소1");
		info.setAddress2("주소2");
		log.info("...........InsertUserInfo.........."+mapper.insertUserInfo(info));
	}
	
	/*
	 * @Test public void insertOauth() { OAuth auth = new OAuth();
	 * auth.setUsername("a"); auth.setKakaoid("aa");
	 * log.info(".....InsertOAuth : "+mapper.insertOAuth(auth)); }
	 */
	
	public void read_security() {
		log.info(mapper.authenticate("user1").toString());
	}
	
	/*
	 * public void kakaoLogin() { log.info(mapper.authenticate("aa").toString()); }
	 */

	
	public void validateTest() {
		log.info(String.valueOf(mapper.validateUser("b")));
	}
	
	public void getUserInfo() {
		log.info(mapper.getUserInfo("user1").toString());
	}
	
	public void updateUserInfo() {
		UpdateInfo info = new UpdateInfo();
		info.setUsername("user1");
		info.setEmail("bbb@a.com");
		info.setInterest("Python");
		info.setSns("N");
		info.setZonecode(54321);
		info.setAddress1("주소1수정");
		info.setAddress2("주소2수정");
		log.info("..........updateUserInfo..........."+mapper.updateUserInfo(info));
	}
	
	
	public void updatePassword() {
		pwencoder = new BCryptPasswordEncoder();
		User user = new User();
		String old = pwencoder.encode("1111");
		log.info(""+pwencoder.matches("1111", old));
		log.info("..........updatePassword..........."+mapper.updatePassword(user));
	}
	
	public void deleteUser() {
		User user = new User();
		user.setUsername("user1");
		user.setPassword("1234");
		log.info("..............deleteUser.............."+mapper.deleteUser(user));
	}
}
