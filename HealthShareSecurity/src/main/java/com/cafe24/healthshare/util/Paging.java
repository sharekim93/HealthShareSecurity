package com.cafe24.healthshare.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Paging {	
	private int listtotal; 		//전체 게시글 수
	private int onepagelist;	//한 페이지에 보여줄 게시글 수
	private int pagetotal;		//총 페이지 계산
	private int bottomlist;		//하단에 페이저 수
	private int pstartno;		//페이지의 스타트번호
	int current_page;			//현재페이저번호
	int start_page;				//시작페이저번호
	int end_page;				//마지막페이저번호
	
	public Paging() {}
	public Paging(int pstartno, int listCnt) {
		this.listtotal 		= listCnt;
		this.onepagelist 	= 10;
		this.pagetotal 		= (int) ((listtotal<=0)? 1:Math.ceil(listtotal/(float)onepagelist));
		this.bottomlist 	= 10;
		this.pstartno 		= pstartno;
		this.current_page 	= (int) (Math.ceil((pstartno +1)/(float)onepagelist));
		this.start_page 	= (int) (Math.floor((current_page-1)/bottomlist)*bottomlist+1);
		this.end_page 		= start_page + bottomlist - 1;
		if(pagetotal < end_page) {this.end_page = pagetotal;}
	}
}
