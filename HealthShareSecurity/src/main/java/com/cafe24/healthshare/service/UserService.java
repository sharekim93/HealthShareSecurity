package com.cafe24.healthshare.service;

import com.cafe24.healthshare.dto.Join;
import com.cafe24.healthshare.dto.UpdateInfo;
import com.cafe24.healthshare.dto.UpdatePass;
import com.cafe24.healthshare.vo.User;
import com.cafe24.healthshare.vo.UserInfo;

public interface UserService {

	public int validateUser(String username);
	public int joinUser(Join join);
	public UserInfo getUserInfo(String username);
	public String getUsernameFromKakao(String code);
	public int updateUserInfo(UpdateInfo info);
	public int updateUserPass(UpdatePass updatepass);
	public int deleteUser(User user);
}
