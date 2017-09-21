<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<%
//전달받은 pageUrl의 값으로 include할 페이지 값을 설정한다.

	String pageUrl = "../board/";
	if (request.getAttribute("pageUrl") == null) {

		pageUrl += "main.jsp";

	} else {
			pageUrl +=request.getAttribute("pageUrl")+".jsp"; 

	}
%>
<meta charset="utf-8" />
<!--
	Editorial by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Editorial by HTML5 UP</title>
<link rel="stylesheet" href="//unpkg.com/bootstrap/dist/css/bootstrap.min.css">
<script type="text/javascript" src="/recipe/resources/ckeditor/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//unpkg.com/jquery/dist/jquery.min.js"></script>
<script src="//unpkg.com/bootstrap/dist/js/bootstrap.min.js"></script>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<!--[if lte IE 8]><script src="resources/assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="/recipe/resources/assets/css/main.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="resources/assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="resources/assets/css/ie8.css" /><![endif]-->
<style type="text/css">
select#soflow, select#searchType {
   -webkit-appearance: button;
   -webkit-border-radius: 2px;
   -webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
   -webkit-padding-end: 20px;
   -webkit-padding-start: 2px;
   -webkit-user-select: none;
   background-image: url(http://i62.tinypic.com/15xvbd5.png), -webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5);
   background-position: 97% center;
   background-repeat: no-repeat;
   border: 1px solid #AAA;
   color: #555;
   font-size: inherit;
   overflow: hidden;
   padding: 5px 10px;
   text-overflow: ellipsis;
   width: 255px;
   float : left;
}

</style>
</head>
<body>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">

					<center><a href="/recipe/?page=main" class="glyphicon glyphicon-user">Recipe</a></center>
					<ul class="icons">
					
					<a href="/recipe/static/index" class="logo">
					
					</a>
					
					<!-- 로그인시 아이디로 게스트인경우 게스트로 뜸 -->
						<c:choose>
						    <c:when test="${sessionScope.sessionId == null}">
						    	Guest로 방문중
						        <li><a href="/recipe/?page=signup" class="glyphicon glyphicon-log-in">로그인
						        <span class="label"></span></a></li>
						    </c:when>
						    <c:otherwise>
						        ${sessionScope.sessionId}님이 로그인중입니다.
						        <li><a href="/recipe/board/logout" class="glyphicon glyphicon-log-out">로그아웃
						        <span class="label"></span></a></li>
						    </c:otherwise>
						</c:choose>
					
						<li><a href="/recipe/?page=bookmark_page" class="glyphicon glyphicon-star">즐겨찾기
						<span class="label"></span></a></li>
 
						<li><a href="/recipe/?page=write" class="glyphicon glyphicon-pencil">글쓰기
						<span class="label"></span></a></li>
						<li><a href="/recipe/?page=myRef" class="glyphicon glyphicon-modal-window">냉장고
						<span class="label"></span></a></li>
					</ul>
					
				</header>
					<div class="content">					
							<jsp:include page="<%=pageUrl%>" flush="true"/>				
																										
</div>
				

			</div>
			
						
		</div>

		<!-- Sidebar -->
		<div id="sidebar">
			<div class="inner">

				<!-- Search -->
				<section id="search" class="alt">
					<form method="post" action="/recipe/board/result">
					
						<input type="text" name="word" id="word" placeholder="Search" >
						<div>
						<select name="searchType" id="searchType" class="searchType">
							<option selected>전체</option>
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="id">작성자</option>
						</select>
						<button type="submit" id ="Searchbtn "value="버튼" class="Searchbtn"><span class="glyphicon glyphicon-search"></span></button>
						</div>
					</form>
				</section>
				
				<!-- Menu -->
				<nav id="menu">
					<header class="major">
						<h2>Menu</h2>
					</header>
					<ul class="nanumbarungothic">
						<li><a href="/recipe/?category=21000">한식</a></li>
						<li><a href="/recipe/?category=22000">양식</a></li>
						<li><a href="/recipe/?category=25000">일식</a></li>
						<li><a href="/recipe/?category=23000">중식</a></li>
						
						<li><a href="/recipe/?category=26000">가정식</a></li>
						<li><a href="/recipe/?category=27000">간편식</a></li>
						<li><a href="/recipe/?category=24000">제철음식</a></li>
							

					</ul>
				</nav>

				<!-- Section -->

			</div>
		</div>

	</div>

	<!-- Scripts -->
	<script src="/recipe/resources/assets/js/jquery.min.js"></script>
	<script src="/recipe/resources/assets/js/skel.min.js"></script>
	<script src="/recipe/resources/assets/js/util.js"></script>
	<script src="/recipe/resources/assets/js/main.js"></script>
	<script>
	
	</script>
</body>
</html>