package com.cafe24.healthshare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearch {
	private String field;
	private String query;
	private int page;
}
