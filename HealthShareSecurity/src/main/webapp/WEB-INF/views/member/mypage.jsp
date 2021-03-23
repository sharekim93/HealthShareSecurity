<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<body>
	<div class="container panel" id="main_panel">
		<h3>마이페이지</h3>
		<table class="table table-striped">
		<caption style="margin-left:0px;">회원정보</caption>
		<tbody>
			<c:set var="u" value="${user}"></c:set>
			<sec:authentication property="principal" var="p"/>
			<c:if test="${!empty p.password}">
			<tr><th scope="row">아이디</th><td>${u.username}</td></tr>
			</c:if>
			<tr><th scope="row">이름</th><td>${u.nickname}</td></tr>
			<tr><th scope="row">이메일</th><td>${u.email}</td></tr>
			<tr><th scope="row">주소</th><td>${u.address1}&nbsp;${u.address2}</td></tr>
			<tr><th scope="row">가입일자</th><td>${u.regdate}</td></tr>
			<tr><th scope="row">관심언어</th><td>${empty u.interest? '-':u.interest}</td></tr>
			<tr><th scope="row">SNS사용여부</th><td>${empty u.sns? '-':u.sns}</td></tr>
		</tbody>
		</table>
		<div class="text-center">
			<input type="button" value="회원정보수정" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/member/editInfo'" style="margin-top:10px;background-color:#337ab7;color:white;">
			<c:if test="${!empty p.password}">
			<input type="button" value="비밀번호수정" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/member/editPass'" style="margin-top:10px;background-color:#337ab7;color:white;">
			</c:if>
			<input type="button" value="탈퇴"		  id="withdrawal" class="btn btn-default" style="margin-top:10px;background-color:#337ab7;color:white;">
		</div>
		<c:if test="${empty p.password}">
			<div style="margin-top:20px;">
				<p class="text-center">카카오 로그인의 경우 비밀번호 수정 기능이 없습니다</p>
			</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/member/delete" id="delete" method="post">
			<input type="hidden" name="username" id="username" value="${u.username}">
			<input type="hidden" name="password" id="password" value="">
			<input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"  />
		</form>
	</div>
</body>
<script>
	$(document).ready(function(){
		$("#withdrawal").click(function(){
			if(!confirm("정말 탈퇴하시겠습니까?")){return false;}
			let pass = prompt("당신의 비밀번호를 입력해주세요");
			if(pass==null){return false;}
			$("#password").attr("value",pass);
			$("#delete").submit();
		});
		
		var result="${result}";
		if(result.length!=0){alert("${result}");}
	});
</script>
<%@include file="../inc/footer.jsp"%>