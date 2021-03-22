package com.cafe24.healthshare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Join {
	private String username;
	private String nickname;
	private String password;
	private String memail;
	private int zonecode;
	private String address1;
	private String address2;
	private String interest;
	private String sns;
	private String oauth;
}
