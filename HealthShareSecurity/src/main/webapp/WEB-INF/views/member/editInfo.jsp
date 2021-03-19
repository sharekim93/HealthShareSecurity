<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body>
	<div class="container panel" id="main_panel">
		<form class="form-group" action="${pageContext.request.contextPath}/member/editInfo" method="post">
			<fieldset>
			<legend>회원정보수정</legend>
			<table class="table table-striped">
				<tbody>
				<c:set var="u" value="${user}"/>
					<tr><td>아이디</td><td><input type="text" name="username" id="id" value="${u.nickname}"></td></tr>
					<tr><td>이름</td><td><input type="text" name="nickname" id="name" value="${u.nickname}"></td></tr>
					<tr><td>이메일</td><td><input type="text" name="email" id="email" value="${u.email}"></td></tr>
					<tr>
						<td>우편번호</td>
						<td>
							<input type="text" id="zonecode" name="zonecode" value="${u.zonecode}">
							<input type="button" id="postcode" class="btn btn-default" value="우편번호찾기"/>
						</td>
					</tr>
					<tr><td>주소</td><td><input type="text" name="address1" id="address1" value="${u.address1}" style="width:450px;"></td></tr>			
					<tr><td>상세주소</td><td><input type="text" name="address2" id="address2" value="${u.address2}"></td></tr>			
					<tr><td>가입날짜</td><td>${u.regdate}</td></tr>
					<tr><td>관심언어</td><td>
						<c:if test="${fn:contains(u.interest,'JAVA')}">
						<input type="checkbox" id="java" name="interest" value="JAVA" checked/>
						</c:if>
						<c:if test="${!fn:contains(u.interest,'JAVA')}">
						<input type="checkbox" id="java" name="interest" value="JAVA"/>
						</c:if>
						<label for="java">자바</label>
						<c:if test="${fn:contains(u.interest,'JSP')}">
						<input type="checkbox" id="jsp" name="interest" value="JSP" checked/>
						</c:if>
						<c:if test="${!fn:contains(u.interest,'JSP')}">
						<input type="checkbox" id="jsp" name="interest" value="JSP" />
						</c:if>
						<label for="jsp">JSP</label>
						<c:if test="${fn:contains(u.interest,'SQL')}">
						<input type="checkbox" id="sql" name="interest" value="SQL" checked/>
						</c:if>
						<c:if test="${!fn:contains(u.interest,'SQL')}">
						<input type="checkbox" id="sql" name="interest" value="SQL" />
						</c:if>
						<label for="sql">SQL</label>
						<c:if test="${fn:contains(u.interest,'PYTHON')}">
						<input type="checkbox" id="python" name="interest" value="PYTHON" checked/>
						</c:if>
						<c:if test="${!fn:contains(u.interest,'PYTHON')}">
						<input type="checkbox" id="python" name="interest" value="PYTHON"/>
						</c:if>
						<label for="python">Python</label>
						</td></tr>
					<tr><td>SNS사용여부</td>
						<td><select id="interest" name="sns">
						<option value = "-">---선택하세요---</option>
						<option value = "Y" ${(u.sns eq 'Y')? "selected":""}>사용</option>		
						<option value = "N" ${(u.sns eq 'N')? "selected":""}>미사용</option>
						</select>
						</td></tr>
				</tbody>
			</table>
			<div class="form-group">
				<input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"  />
			</div>
			<div class="text-center">
				<input type="submit" id="submit" value="수정" class="btn btn-default" style="background-color:#337ab7;color:white;" >
				<a href="${pageContext.request.contextPath}/member/mypage" id="before" class="btn btn-default" style="background-color:#337ab7;color:white;" >이전</a>
			</div>
			</fieldset>
		</form>
	</div>
</body>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
$(document).ready(function(){
	$("#submit").click(function(){
		if($("#name").val()==""){alert("이름를 입력해주세요");return false;}
		if($("#email").val()==""){alert("이메일을 입력해주세요");return false;}
	});
	$("#postcode").click(function(){
		new daum.Postcode({
			oncomplete:function(data){
				$("#zonecode").val(data.zonecode);
				$("#address1").val(data.roadAddress);
				$("#address2").val("");					
				$("#address2").focus();					
			}
		}).open();
	});
})
</script>
<%@include file="../inc/footer.jsp"%>