package com.cafe24.healthshare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Board {
	private int bno;
	private String btitle;
	private String username;
	@JsonFormat(pattern = "yyyyMMdd")  
	private String bdate;
	private int	   bhit;
	private String bcontent;
	private String bip;
	private String bfile;
}
