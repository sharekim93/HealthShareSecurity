<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<body>
	<div class="container panel panel-info">
		<h2 class="panel-heading">SECURITY</h2>
		<p><a href="${pageContext.request.contextPath}/security/all" class="btn btn-danger">ALL - 모든 사람들</a>
		<p><a href="${pageContext.request.contextPath}/security/member" class="btn btn-danger">MEMBER - 멤버만 접근가능</a>
	</div>
</body>
</html>