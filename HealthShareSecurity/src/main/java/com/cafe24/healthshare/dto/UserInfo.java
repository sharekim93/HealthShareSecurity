package com.cafe24.healthshare.dto;

import lombok.Data;

@Data
public class UserInfo {
	private String username;
	private String email;
	private String regdate;
	private String ip;
	private String interest;
	private String sns;
	private int zonecode;
	private String address1;
	private String address2;
}
