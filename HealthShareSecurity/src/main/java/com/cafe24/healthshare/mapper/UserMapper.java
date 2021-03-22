package com.cafe24.healthshare.mapper;

import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.vo.Auth;
import com.cafe24.healthshare.vo.OAuth;
import com.cafe24.healthshare.vo.User;
import com.cafe24.healthshare.vo.UserInfo;

public interface UserMapper {
	public int insertUser(User user);
	public int insertAuth(Auth auth);
	public int insertUserInfo(UserInfo info);
	public int insertOAuth(OAuth oauth);
	public User authenticate(String username);
	public UserInfo getUserInfo(String username);
	public int validateUser(String username);
	public int updateUserInfo(UpdateInfo info);
	public int updatePassword(User user);
	public int deleteUser(User user);
}
