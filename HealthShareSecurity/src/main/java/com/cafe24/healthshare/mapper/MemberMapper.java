package com.cafe24.healthshare.mapper;

import com.cafe24.healthshare.dto.Auth;
import com.cafe24.healthshare.dto.User;
import com.cafe24.healthshare.dto.UserInfo;

public interface MemberMapper {
	public int insertUser(User user);
	public int insertAuth(Auth auth);
	public int insertUserInfo(UserInfo info);
	public User read_security(String userid);
	public int updateUserInfo(UserInfo info);
	/* public int updatePassword(User user); */
	public int deleteUser(User user);
}
