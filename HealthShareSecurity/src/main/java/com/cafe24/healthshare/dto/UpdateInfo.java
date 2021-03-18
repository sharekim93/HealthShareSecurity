package com.cafe24.healthshare.dto;

import lombok.Data;

@Data
public class UpdateInfo {
	private String username;
	private String nickname;
	private String email;
	private int zonecode;
	private String address1;
	private String address2;
	private String interest;
	private String sns;
}
