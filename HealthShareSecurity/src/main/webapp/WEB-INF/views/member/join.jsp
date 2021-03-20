<%@include file="../inc/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
	<div class="container panel" id="main_panel">
		<h3>회원가입</h3>
		<h5>(*)은 필수 입력 사항입니다.</h5>
		<br/>
		<form action="${pageContext.request.contextPath}/member/join" method="post">
			<fieldset>
				<legend>필수정보입력</legend>
				<div class="form-group">
					<label for="mid">(*) 아이디</label>
					<div class="row">
						<div class="col-sm-4">
							<input type="text" id="mid" name="username" class="form-control" value="${cookie.join_id.value}" placeholder="아이디를 입력하세요"/>		
						</div>
						<div class="col-sm-8">
							<input type="button" id="checkId" class="btn btn-default" value="아이디중복확인">
						</div>
					</div>
				<div>
					<p class="text-left" id="idCheckResult" style="color:royalblue;margin-top: 7px; margin-bottom: 7px;">아이디 중복확인을 해주세요</p>
				</div>
				</div>
				<div class="form-group">
					<label for="mname">(*) 이름</label>
					<input type="text" id="mname" name="nickname" class="form-control" placeholder="이름을 입력하세요">
				</div>
				<div class="form-group">
					<label for="mpass">(*) 비밀번호</label>
					<input type="password" id="mpass" name="password" class="form-control" placeholder="비밀번호를 입력하세요">
				</div>
				<div class="form-group">
					<label for="mpass_chk">(*) 비밀번호 확인</label>
					<input type="password" id="mpass_chk" class="form-control" placeholder="비밀번호를 다시 한 번 입력하세요">
				</div>
				<div class="form-group">
					<label for="memail">(*) 이메일</label>
					<input type="email" id="memail" name="memail" class="form-control" placeholder="이메일을 입력하세요">
				</div>
				<div class="form-group">
					<label for="row">(*) 주소</label>
					<div class="row">
						<div class="col-sm-4">
							<input type="text" id="zonecode" name="zonecode" title="우편번호" placeholder="우편번호" class="form-control">
						</div>
						<div class="col-sm-8">
							<input type="button" id="postcode" value="우편번호찾기" class="btn btn-default" />
						</div>
					</div>
					<div class="row" style="margin-top:5px;">
						<div class="col-sm-7">
							<input type="text" id="address1" name="address1" title="주소" placeholder="주소" class="form-control">
						</div>
						<div class="col-sm-5" style="padding-left:0px;">
							<input type="text" id="address2" name="address2"title="상세주소" placeholder="상세주소" class="form-control">
						</div>
					</div>
				</div>
				<div class="form-group">
					<p class="text-left">
						<input type="checkbox" id="fourteen">
						<label for="fourteen">만 14세 이상입니다.</label>
					</p>
				</div>
				<legend>선택정보</legend>
				<c:if test="${!(empty param.kakaoid)}">
				<div class="form-group">
					<p class="text-left">
						<input type="checkbox" id="kakao" name="kakao" checked>
						<input type="hidden" name="kakao_id" value="${param.kakaoid}">
						<label for="kakao">카카오 계정과 연동합니다</label>
					</p>
				</div>
				</c:if>
				<div class="form-group">
				<h5>관심 언어 선택</h5>
					<p class="text-left">
						<input type="checkbox" id="java" name="interest" value="JAVA">
						<label for="java">자바</label>
						<input type="checkbox" id="jsp" name="interest" value="JSP">
						<label for="jsp">JSP</label>
						<input type="checkbox" id="sql" name="interest" value="SQL">
						<label for="sql">SQL</label>
						<input type="checkbox" id="python" name="interest" value="PYTHON">
						<label for="python">Python</label>
					</p>
				</div>
				<div class="form-group">
				<h5>SNS 사용여부</h5>
					<select id="mselect" name="sns">
						<option value = "-">---선택하세요---</option>
						<option value = "Y">사용</option>		
						<option value = "N">미사용</option>
					</select>
				</div>
				<div class="form-group">
					<input type="submit" class="btn btn-primary form-control" name="join" value="회원가입하러가기" id="submit">
				</div>
				<div class="form-group">
				<a class="btn btn-default form-control" href="javascript:history.go(-1);">이전</a>
				</div>
				<div class="form-group">
					<input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"  />
				</div>
			</fieldset>
		</form>
	</div>
</body>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		var usableId = -1;
		
		$("#submit").click(function(){
			if($("#mid").val()==""){alert("아이디를 확인해주세요");$("#mid").focus();return false;}
			if($("#mname").val()==""){alert("이름을 확인해주세요");$("#mname").focus();return false;}
			if($("#mpass").val()==""){alert("비밀번호를 확인해주세요");$("#mpass").focus();return false;}
			if($("#mpass_chk").val()==""){alert("비밀번호를 확인해주세요");$("#mpass_chk").focus();return false;}
			else if($("#mpass").val()!=$("#mpass_chk").val()){alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");return false;}
			if($("#zonecode").val()==""){alert("주소를 입력해주세요");$("#postcode").focus();return false;}
			if($("#address2").val()==""){alert("상세 주소를 입력해주세요");$("#address2").focus();return false;}
			if($("#memail").val()==""){alert("이메일을 확인해주세요");$("#memail").focus();return false;}
			if(!$("input:checkbox[id='fourteen']:checked").length){alert("만 14세 이상만 가입이 가능합니다.");return false;}
			if(usableId<0){alert('아이디 확인 후 가입 가능합니다.');$("#checkId").focus();return false;}
		});
		
		$("#checkId").click(function(){
			let id = $("input[id='mid']").val();
		if(id==""){$("#idCheckResult").html('공백이 입력되었습니다.');return false;}
		$.ajax({
			url:"${pageContext.request.contextPath}/member/idCheck",
			method:"get",
			data:{userid:id},
			success:function(data){
				if(data==0){$("#idCheckResult").html("사용 가능한 아이디입니다.");usableId=true;}
				else{$("#idCheckResult").html("이미 사용중인 아이디입니다.");}
			}
		});
		});
		
		$("#postcode").click(function(){
			new daum.Postcode({
				oncomplete:function(data){
					$("#zonecode").val(data.zonecode);
					$("#address1").val(data.roadAddress);
					$("#address2").focus();					
				}
			}).open();
		});
	});
</script>

<%@include file="../inc/footer.jsp"%>