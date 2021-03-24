package com.cafe24.healthshare.dto;

import lombok.Data;

@Data
public class Board {
	private int bno;
	private String btitle;
	private String username;
	private String bdate;
	private int	   bhit;
	private String bcontent;
	private String bip;
	private String bfile;
}
