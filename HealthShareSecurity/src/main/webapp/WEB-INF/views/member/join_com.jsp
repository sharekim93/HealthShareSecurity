<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<body>
	<div class="container panel" id="main_panel">
		<h3>FORM BASIC-JOIN</h3>
		<h5>회원가입을 축하합니다.</h5>
		<table class="table table-striped">
		<caption style="padding-left:0px;">회원가입완료</caption>
		<tbody>
			<c:set var="m" value="${info}"></c:set>
			<tr><th scope="row">아이디</th><td>${m.username}</td></tr>
			<tr><th scope="row">이름</th><td>${m.nickname}</td></tr>
			<tr><th scope="row">이메일</th><td>${m.email}</td></tr>
			<tr><th scope="row">주소</th><td>${m.address1}</td></tr>
			<tr><th scope="row">가입일자</th><td>${m.regdate}</td></tr>
			<tr><th scope="row">카카오 간편가입여부</th><td>${(empty kakao_id)? 'N':'Y'}</td></tr>
			<tr><th scope="row">관심언어</th><td>${empty m.interest? '-':m.interest}</td></tr>
			<tr><th scope="row">SNS사용여부</th><td>${m.sns}</td></tr>
		</tbody>
		</table>
		<div class="text-right">
			<input type="button" onclick="location.href='${pageContext.request.contextPath}/member/login'" value="LOGIN" id="login" class="btn btn-blue">
		</div>
	</div>
</body>
<%@include file="../inc/footer.jsp"%>