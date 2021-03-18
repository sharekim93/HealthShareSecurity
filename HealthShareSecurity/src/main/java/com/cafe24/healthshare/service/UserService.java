package com.cafe24.healthshare.service;

import java.net.UnknownHostException;

import com.cafe24.healthshare.dto.Join;
import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.dto.UpdatePass;
import com.cafe24.healthshare.vo.User;
import com.cafe24.healthshare.vo.UserInfo;

public interface UserService {

	public int validateUser(String username);
	public int joinUser(Join join) throws UnknownHostException;
	public UserInfo getUserInfo(String username);
	public int updateUserInfo(UpdateInfo info);
	public int updateUserPass(UpdatePass updatepass);
	public int deleteUser(User user);
}
