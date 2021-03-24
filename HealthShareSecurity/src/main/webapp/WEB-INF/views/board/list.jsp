<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<div class="container panel board">
		<c:choose>
		<c:when test="${param.c eq '1'}"><h3>자유게시판</h3></c:when>
		<c:when test="${param.c eq '2'}"><h3>공지사항</h3></c:when>
		<c:when test="${param.c eq '4'}"><h3>비밀글게시판</h3></c:when>
		</c:choose>
		<div class="search-form margin-top">
			<h4 class="hidden">검색 폼</h4>
			<div class="table-form" >
				<fieldset style="text-align:right;">
				<legend class="hidden">검색 필드</legend>
				<label class="hidden"> 검색 분류</label>
				<input type="hidden" name="c" value="${(!empty param.c)? param.c:'1'}"/>
				<select name="f" id="sch_field">
					<option value="btitle">제목</option>
					<option value="bname">작성자</option>
				</select>
				<label class="hidden">검색어</label>
				<input type="hidden" name="p" value="${(empty param.p)? 1:param.p }"/>
				<input type="text" name="q" id="q" value="${param.q}">
				<input type="button" id="search" value="검색" class="btn btn-search">
				</fieldset>
			</div>		
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col" colspan="2">TITLE</th>
					<th scope="col">WRITER</th>
					<th scope="col">DATE</th>
					<th scope="col">HIT</th>
					<!-- <th scope="col" class="text-right">수정/삭제</th> -->
				</tr>
			</thead>
		<tbody>		</tbody>			
		</table>
		
		<!-- 페이지 번호 -->
		<div class="indexer margin-top">
			<h3 class="hidden">현재 페이지</h3>
			<div>
				<span id="pageIndex">	</span>pages
			</div>
		</div>
		
		<!-- 글쓰기 -->
		<div class="text-right" style="margin-top:10px;">
			<a href="gowrite.board?c=${(!empty param.c)? param.c:'1'}" class="btn btn-default" style="background-color: #337ab7; color:white;">글쓰기</a>			
		</div>
		
		<!-- 페이저 -->
		<div class="margin-top text-center">
			<div class="pagination">		</div>
		</div>
		
		<!-- hidden delete -->
		<form id="delete">
			<input type="hidden" name="bpass" id="bpass" value="">
			<input type="hidden" name="bno" id="bno" value="">
			<input type="hidden" name="c" id="c" value="${(!empty param.c)? param.c:'1'}">
		</form>
	</div>
</body>
<script>	
	$(document).ready(function(){
		
		getboard(pageNum,field,query,category);
		setIndex(pageNum,pageAll);
		setPager(pageNum,startNum,lastNum);
		
		$("#btn_edit").click(function(){
			if(!confirm('수정하시겠습니까?')){return false;}
		});
		
		$("#d_delete").click(function(){
			if(!confirm("정말 삭제하시겠습니까?")){return false;}
			let pass = prompt("비밀번호를 입력해주세요");
			if(pass==null){return false;}
			$("#bpass").attr("value",pass);
			$("#bno").attr("value",$("#d_delete").val());
			$("#delete").attr({action:"delete.board",method:'post'}).submit();
		});

		$(".btn-prev").on('click',function(){
			pageNum=pageNum-1;
			getboard(pageNum,field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
		});
		
		$(".page").on('click',function(){
			pageNum=$(this).text();  //num
			getboard(pageNum,field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
			
		});
		
		$(".btn-next").on('click',function(){
			pageNum=Number.parseInt(pageNum)+1;
			getboard(pageNum,field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
		});
		
		$(".btn-search").click(function(){
			field=$("#sch_field").val();
			query=$("#q").val();
			find(field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
		});
	});
	
	var pageNum 	= 1; // 현재 페이지
	var startNum 	= 1;
	var lastNum 	= 1;
	var totalCnt	= 1; // 총 게시글 수
	var listCnt		= 1; // 페이저에 나타날 개수
	var pageLimit 	= 1; // 한 페이지에 보일 레코드 수
	var query 		= $("#q").val();
	var field 		= $("#sch_field").val();
	var category 	= $("#c").val();
		
	function getboard(pageNum,field,query,category){
		$.ajax({
			url:"getlist.board",
			type:"get",
			async:false,
			data:{p:pageNum,f:field,q:query,c:category},
			datatype:"json",
			success:function(data){
				let table ="";
				let obj = JSON.parse(data);
				
				totalCnt = obj.count;
				pageNum = obj.page;
				pageAll = obj.pageAll;
				startNum = obj.startNum;
				lastNum	 = obj.lastNum;
				
				$(".table tbody").html("");
				for(i in obj.list){
					table +="<tr>";
					table +="<td>" + (totalCnt-10*(pageNum-1)-i) + "</td>";
					if(category!=4){
					table +="<td colspan='2'><a href='detail.board?bno="+obj.list[i].bno+"&c="+category+"'>" + obj.list[i].btitle + "</a></td>";
					}
					else{
					table +="<td colspan='2'><a href='secret_pass.board?bno="+obj.list[i].bno+"'>" + obj.list[i].btitle + "</a></td>";	
					}
					table +="<td>" + obj.list[i].bname + "</td>";
					table +="<td>" + obj.list[i].bdate + "</td>";
					table +="<td>" + obj.list[i].bhit + "</td>";
					/* table +="<td class=text-right>"; 
					table +="<a id='btn_edit' href='goupdate.board?bno="+obj.list[i].bno+"&c="+category+"'"
					table +="class='btn btn-danger btn_func'>수정</a>";
					table +="<button id='d_delete' class='btn btn-danger btn_func' value='"+obj.list[i].bno+"'>삭제</button>";
					table +="</td>"; */
					table +="</tr>";
				}
				$(".table tbody").html(table);
			}
		});
	};
	
	function find(field,query,category){
		$.ajax({
			url:"getlist.board",
			type:"get",
			async:false,
			data:{f:field,q:query,c:category},
			datatype:"json",
			success:function(data){
				let table ="";
				let obj = JSON.parse(data);
				
				pageNum = obj.page;
				pageAll = obj.pageAll;
				startNum = obj.startNum;
				lastNum	 = obj.lastNum;
				
				$(".table tbody").html("");
				for(i in obj.list){
					table +="<tr>";
					table +="<td>" + obj.list[i].bno + "</td>";
					if(category!=4){
						table +="<td colspan='2'><a href='detail.board?bno="+obj.list[i].bno+"&c="+category+"'>" + obj.list[i].btitle + "</a></td>";
						}
					else{ table +="<td colspan='2'><a href='secret_pass.board?bno="+obj.list[i].bno+"'>" + obj.list[i].btitle + "</a></td>"; }
					table +="<td>" + obj.list[i].bname + "</td>";
					table +="<td>" + obj.list[i].bdate + "</td>";
					table +="<td>" + obj.list[i].bhit + "</td>";
					/* table +="<td class=text-right>"; 
					table +="<a id='btn_edit' href='goupdate.board?bno="+obj.list[i].bno+"&c="+category+"'"
					table +="class='btn btn-danger btn_func'>수정</a>";
					table +="<button id='d_delete' class='btn btn-danger btn_func' value='"+obj.list[i].bno+"'>삭제</button>";
					table +="</td>"; */
					table +="</tr>";
				}
				$(".table tbody").html(table);
			}
		});
	};
	
	function setIndex(pageNum,pageAll){
		$("#pageIndex").html(pageNum+"/"+pageAll+"&nbsp");		
	}
	
	function setPager(pageNum,startNum,lastNum){
		let pager="";
		if(pageNum>1){ pager += "<a class='btn btn-prev'>이전</a>"; }
		for(let page=startNum;page<=lastNum;page++){ 
			if(pageNum==page){pager+="<a class='active page'>"+  page  +"</a>";}
			else { pager+="<a class='page'>"+  page  +"</a>";} }  /////
		if(pageNum<lastNum){ pager +="<a class='btn btn-next'>다음</a>"; }
		$(".pagination").html(pager);
		
		$(".page").on('click',function(){
			pageNum=$(this).text();  //num
			getboard(pageNum,field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
			
		});
		
		$(".btn-prev").on('click',function(){
			pageNum=pageNum-1;
			getboard(pageNum,field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
		});
		
		$(".btn-next").on('click',function(){
			pageNum=Number.parseInt(pageNum)+1;
			getboard(pageNum,field,query,category);
			setIndex(pageNum,pageAll);
			setPager(pageNum,startNum,lastNum);
		});
		
	}


</script>
<%@include file="../inc/footer.jsp"%>