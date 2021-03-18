package com.cafe24.healthshare.service_test;

import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.healthshare.dto.Join;
import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.dto.UpdatePass;
import com.cafe24.healthshare.service.UserService;
import com.cafe24.healthshare.vo.User;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class ServiceTest {

	@Autowired
	private UserService service;
	
	
	public void validateUserTest() {
		log.info(String.valueOf(service.validateUser("user1")));
	}
	
	
	public void joinUserTest() throws UnknownHostException {
		Join join = new Join();
		join.setUsername("user2");
		join.setNickname("테스트");
		join.setPassword("1234");
		join.setMemail("test@Test.com");
		join.setZonecode(123456);
		join.setAddress1("주소1");
		join.setAddress2("주소2");
		join.setInterest("SQL");
		join.setSns("N");
		
		log.info(String.valueOf(service.joinUser(join)));
	}
	
	public void getUserInfoTest() {
		log.info(service.getUserInfo("user2").toString());
	}
	
	
	public void updateUserInfoTest() {
		UpdateInfo info = new UpdateInfo();
		info.setUsername("user3");
		info.setNickname("바꿔");
		info.setEmail("내이메일");
		info.setZonecode(1313);
		info.setAddress1("주소바꿈요");
		log.info(String.valueOf(service.updateUserInfo(info)));
	}
	
	
	public void updatePassTest() {
		UpdatePass updatepass = new UpdatePass();
		updatepass.setUsername("user2");
		updatepass.setOld("1111");
		updatepass.setNew_("1234");
		log.info(String.valueOf(service.updateUserPass(updatepass)));
	}
	
	@Test
	public void deleteUserTest() {
		User user = new User();
		user.setUsername("user2");
		user.setPassword("1234");
		log.info(String.valueOf(service.deleteUser(user)));
	}
}
