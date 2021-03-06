<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>HealthShare Spring Security</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/icon.png" type="image/x-icon">
  <link rel="icon" href="${pageContext.request.contextPath}/resources/icon.png" type="image/x-icon">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
  nav.navbar.gnb   { padding: 10px 0;  margin: 0; }
  nav.navbar.gnb a { font-size: 22px; font-weight: bold; }
  nav.navbar.gnb a.first { color: darkorange; }
  .form-group.myform { border: 1px solid #337ab7; }
  input#header_search { border: 0 none;  box-shadow: none;  }
  button.header_form_go { border: 0 none; background-color: #337ab7; color: white; padding: 8px; }
  nav.navbar.gnb .nav>li>a:focus,   nav.navbar.gnb .nav>li>a:hover {
    text-decoration: underline;
    background-color:white; 
  }
  
  
  nav.navbar.lnb { background-color: #f3f3f3; margin:0;}
  nav.navbar.lnb  a{ color:#333;   font-weight:bold; }
  nav.navbar.lnb   nav.navbar.gnb .nav>li>a:focus, nav.navbar.lnb  .nav>li>a:hover {
    text-decoration: underline;
    background-color:#ccc; 
    }
  
  footer {height:50px;}
  footer > div {margin-top:30px;}  
    
    #main_panel{margin-top:40px;}
	#login_panel{margin-top:40px; width:400px;}
	#loginImage{width:90%;}
	.btn-login {
		width:183px;
		height:45px;
		background-color:royalblue;
		color:white;
		font-size:15px;
		font-weight:400;
		margin-right:2px;
	}
	.btn-login:hover{
		color:white;
		border:white;
	}
	.login-btns{display:flex;}
	.btn-blue{
		height:34px;
		width:116px;
		background-color:#337ab7;
		color:white;
		font-size:15px;
		font-weight:400;
		margin-right:2px;
	}
	
	.btn-blue:hover{
		height:34px;
		width:116px;
		color:white;
		border:white;
	}
	
	#checkId{
		height:33px;
		background-color:#f3f3f3;
		font-weight:500;
	}
	
	#postcode{
		height:33px;
		background-color:#f3f3f3;
		font-weight:500;
	}
	
	#main {
		width: 100%;
		min-width:1280px;
	}
	#main .section1{
		width: 100%;
		height: 400px;
		margin : 0 auto;
		background-color: royalblue;
	}
	
	#main .section2{
		width: 100%;
		height:400px;
		margin: 0 auto;
	}
	
	#main .section3{
		width: 100%;
		height:530px;
		background-color:#f3f4f4;
		margin: 20px auto;

	}
	#main .section4{
		width: 100%;
		height:500px;
		margin: 20px auto;
	}
	
	#main .section5{
		width: 100%;
		margin: 20px auto;
		min-height:250px;
	}
	
	#main .video_section{
		width:100%;
		margin: 20px auto;
	}
	
	/* wrappers */
	
	.sectionWrap{margin:0 auto; width:1200px; padding-top:10px;}
	
	.wrapper{display:flex; margin:0 160px 0 0; width:1200px; padding-top:20px;}
	.wrapper h3{margin:0;}
	.wrapper .container {padding:0; margin-left:50px;}
	
	.main_search{
		border:2px; 
		display:flex; 
		margin:40px auto 0; 
		width:40%;
	}
	.title_center{
		text-align:center;
	    font-size: xx-large;
	    font-weight: 600;
	    margin-top:100px;
	    color:white;
	    font-family:auto;
    }
    
	.board{margin-top:20px;}
	.boardArticle{float:right;margin:20px auto;height:315px;border-radius:1%;}
	.picture{margin:0 auto;}
	.title{
		text-align:left;
	    font-size: x-large;
	    font-weight: 600;
	    width:1200px;
	    margin :20px auto;
   		padding-top:20px;
	}
	.table {margin-top:10px;}
	.table>tbody>tr>td{vertical-align:middle;}
	.btn_func{margin:1px;}
	.indexer{text-align:right; margin-right:10px;}
	.boardArticle{
		position: relative;
		margin-top:0;
	    border: 1px solid #e6e6e6;
	    background-color: #fff;}
	.tab-content {padding: 15px 20px; display:block;}
	.tab-content .tab-pane ul{list-style: none;padding:0;}
	.tab-content li {
		overflow: hidden;
	    position: relative;
	    padding-right: 90px;
	    line-height: 34px;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    display: list-item;
	    text-align: -webkit-match-parent;
	    margin:0;
	}
	.tab-content li a{color:#333;text-decoration:none;}
	.tab-content li .date{
		position: absolute;
	    top: 0;
	    right: 0;
	    color: #666;
	}
	.btn_back{float:right;}
	.btn_back a{
		margin-left: 7px;
	    padding: 10px 12px 10px 11px;
	    font-size: 14px;
	    border-color: #ddd;
	    border-radius: 0;
	    position: relative;
	    width: auto;
	    display: inline-block;
	    *display: inline;
	    vertical-align: middle;
	    text-decoration: none !important;
	    color: #000;
	    font-size: 12px;
	    line-height: 100%;
	    font-weight: normal;
	    line-height: 12px;
	    text-decoration: none;
	    overflow: visible;
	    text-align: center;
	    position: relative;
	    display: inline-block;
	    background: #f8f8f8;
	    -moz-border-radius: 3px;
	    -webkit-border-radius: 3px;
	    border-radius: 3px;
	    box-sizing: border-box;
	    border: solid 1px #c0c4cb;
	    border-bottom: 1px solid #a8aeb6;
	    white-space: nowrap;
	    vertical-align: top;
	    cursor: pointer;
	    overflow: visible;
	    zoom: 1;
	}
	
	.search-form.margin-top select, .search-form.margin-top input {
    vertical-align: middle;
	}
	
	.panel-default>.panel-heading {
    color: #333;
    background-color: #337ab7;
    border-color: #337ab7;
    color: white;
    font-weight: bold;
	}
	
	li.list-group-item {
    border-color: #337ab7;
	}
	
	.collapse {
    border: 1px solid #337ab7;
 
	}
	.panel-group .panel-heading+.panel-collapse>.list-group, .panel-group .panel-heading+.panel-collapse>.panel-body {
	    border-top: none;
	}
	
	/* ???????????? css */
	.container.panel.subpop {
    position: fixed;
    top: 20%;
    left: 7%;
    width: 400px;
    color: white;
    background-color: rgba(0,0,0,0.8);
    z-index: 10;
	}
	
	.container.panel.subpop > h3{
	margin: 20px 0 0 0;
    background-color: royalblue;
    color: white;
    border-color: royalblue;
    text-align: center;
    }
    
	.container.panel.subpop > p > strong > a{
	color:darkorange;
	}
	
	.introduce{
		width:100%
	}
  </style>
</head>
<body>

<nav class="navbar gnb">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/member/login"  >HEALTH SHARE</a>
    </div>    
    <ul class="nav navbar-nav navbar-right">
		<li><a href="${pageContext.request.contextPath}/introduce" class="first">????????????</a></li>
		<li><a href="http://sharekim93.cafe24.com/HealthShare">JSP ???????????????</a></li>
    </ul>
  </div>
</nav>
<nav class="navbar lnb">
  <div class="container-fluid">
    <ul class="nav navbar-nav">
      <li><a href="http://sharekim93.cafe24.com/HealthShare">JSP??????????????? ????????????</a></li>
      <li><a href="http://sharekim93.cafe24.com/HealthShareSecurity">SPRING ??????????????? ????????????</a></li>
    </ul> 
	<ul class="nav navbar-nav navbar-right">
		<sec:authorize access="isAuthenticated()">
			<li><a href='${pageContext.request.contextPath}/member/mypage'><strong>???????????????</strong></a></li>
			<li>
				<a href="#" onclick="document.getElementById('logout-form').submit();">????????????</a>
				<form id="logout-form" action='<c:url value='/member/logout'/>' method="POST">
				   <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
				</form>
			</li>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
			<li><a href='${pageContext.request.contextPath}/member/login'><strong>?????????</strong></a></li>
			<li><a href='${pageContext.request.contextPath}/member/joinAgree'><strong>????????????</strong></a></li>
		</sec:authorize>
	</ul>
  </div>
</nav> 
 
 <!-- https://www.w3schools.com/howto/howto_css_subnav.asp -->