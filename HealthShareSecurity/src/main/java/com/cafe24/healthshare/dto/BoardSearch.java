package com.cafe24.healthshare.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardSearch {
	private String field;
	private String query;
	private int page;
	
	public BoardSearch() {}
	public BoardSearch(int page) {this.page=page;}
	public BoardSearch(String field, String query, int page) {
		this.field	= field;
		this.query	= query;
		this.page	= page;
	}
}
