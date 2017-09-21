<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<link rel="stylesheet" href="/recipe/resources/recipe/css/mainPage.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<div id="main">
	<div class="inner">
		<section>
			<header class="major">
				<h2 id="menutxt">북마크 목록</h2>
			</header>
			<div class="container all">
		
			<c:forEach items="${list}" var="list" varStatus="status">
					<div class="col-md-3 col-sm-6" style="cursor: pointer;" onclick="location.href='/recipe/?no=${list.recipe_no}&page=readContent'">
						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<div class="col-img-responsive02">
									<span>${list.recipe_title}</span>
								</div>
								<img src="${thumbFile[status.index].thumbnail_path}" width="200px" height="200px" /></div>
								
								<div class="panel-body">
									<h4>${list.recipe_title}</h4>
									<p>${list.usr_id }</p>
								</div>
							</div>
							</div>
				</c:forEach>
			</div>
			
		</section>


	</div>
</div>
<script>
console.log("메롱");
</script>