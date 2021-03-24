<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>
	<div class="container">
		<h3>글쓰기</h3>
		<form action="${pageContext.request.contextPath}/board/write" method="post" enctype="multipart/form-data">
			<fieldset>
				<div>
					<input type="hidden" name="c" value="${(!empty param.c)? param.c:'1'}"/>
				</div>
				<div class="form-group">
					<label for="bname">작성자</label>
					<input type="text" id="bname" name="username" class="form-control" >
				</div>
				<div class="form-group">
					<label for="btitle">제목</label>
					<input type="text" id="btitle" name="btitle" class="form-control" placeholder="제목을 입력하세요">
				</div>
				<div class="form-group">
					<label for="bcontents">내용</label>
					<textarea rows=10 id="bcontents" name="bcontent" class="form-control" placeholder="내용을 입력하세요"></textarea>
				</div>
				<div class="form-group">
				  <label for="file"  >이미지 업로드</label>
				  <input type="file" name="img" id="file"  class="form-control" >
				</div>	
				<div class="form-group" style="float:right;" >
					<input type="submit" value="입력" id="submit" class="btn btn-default">
					<a href="list.board" class="btn btn-default">목록보기</a>
				</div>
			</fieldset>
		</form>
	</div>
	<script>
	$(document).ready(function(){
		$("#submit").click(function(){
			if($("#bname").val()==""){alert("이름에 빈 칸이 입력되었습니다.");$("#bname").focus();return false;}
			else if($("#bpassword").val()==""){alert("비밀번호에 빈 칸이 입력되었습니다.");$("#bpassword").focus();return false;}
			else if($("#btitle").val()==""){alert("제목에 빈 칸이 입력되었습니다.");$("#btitle").focus();return false;}
			else if($("#bcontents").val()==""){alert("내용에 빈 칸이 입력되었습니다.");$("#bcontents").focus();return false;}
		})
	})
	</script>
</body>
<%@include file="../inc/footer.jsp"%>