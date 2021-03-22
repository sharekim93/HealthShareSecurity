<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<body>
	<div class="container panel" id="login_panel">
		<h3 class="hidden">로그인 페이지</h3>
		<p class="text-center"><img id="loginImage" src="${pageContext.request.contextPath}/resources/images/new_login_img.png" ></p>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<fieldset>
			<legend>LOGIN</legend>
				<div class="form-group">
					<label for="mid">아이디</label>
					<input type="text" id="mid" name="username" class="form-control" placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<label for="mpass">비밀번호</label>
					<input type="password" id="mpass" name="password" class="form-control" placeholder="비밀번호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="checkbox" id="remember_me" name="remember-me">
					<label for="remember_me">로그인 유지</label>
				</div>
				<div class="form-group">
					<div class="login-btns">
					<input type="submit" class="btn btn-login" value="로그인" id="submit">
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=1def83c8f6ac181e4df9db51d15d7f0e&
							redirect_uri=http://sharekim93.cafe24.com/HealthShareSecurity/KakaoLogin&response_type=code">
					<img src="${pageContext.request.contextPath}/resources/images/kakao_login_medium_narrow.png"></a>
					<input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"  />
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<c:if test="${empty cookie.subpop}">
	<div class="container panel subpop">
		<h3 class="panel-heading">안내 말씀</h3>
		<br/>
		<p class="first">현재 접속하신 페이지는 <strong>스프링 포트폴리오</strong> 페이지 입니다</p>
		<p>Spring 포트폴리오의 경우<br/> 현재 스프링시큐리티를 이용한 CRUD와 <br/><strong><a href="${pageContext.request.contextPath}/introduce">자기소개 페이지(클릭)</a></strong>로 구성되어 있습니다.</p>
		<p><strong><a href="http://sharekim93.cafe24.com/HealthShare">JSP포트폴리오(클릭)</a></strong>에서 보다 다양한 기술을 확인하실 수 있습니다.</p>
		<p class="text-right">
			<input type="button" class="btn btn-default" id="close" value="닫기">
		</p>	
	</div><!-- End Subpop -->
	</c:if>
</body>
<script>
$(document).ready(function(){
	
	var result="${result}";
	if(result.length!=0){alert("${result}");}
	
	$("#submit").click(function(){
		if($("#mid").val()==""){alert("아이디를 입력해주세요");return false;}
		if($("#mpass").val()==""){alert("비밀번호를 입력해주세요");return false;}
	});
	
	// 공지사항 팝업 버튼 함수
	$("#close").click(function(){
		$(".subpop").hide();
	});
});
</script>
</html>
<%@include file="../inc/footer.jsp"%>