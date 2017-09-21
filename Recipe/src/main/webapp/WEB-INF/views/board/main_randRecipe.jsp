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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">


<div class="clear" style="width:100%;height:50px;"></div>
<div class="container">
    <header id="myCarousel" class="carousel slide">
    	<div id="myCarousel" class="carousel slide" data-ride="carousel">
	  <div class="carousel-inner" role="listbox">
	  <c:forEach items="${randList}" var="rand" varStatus="status">
	  	<c:if test="${status.index eq 0 }">
			<div class="item active row">
			</c:if>
		<c:if test="${status.index eq 4 }">
			<div class="item row">
			</c:if>
				
				<div class="col-md-3 col-sm-6" style="cursor:pointer" onclick="location.href='/recipe/?no=${rand.recipe_no}&page=readContent'" >
                <div class="panel panel-default text-center">
                    <div class="panel-heading">
					<div class="col-img-responsive02"><span>${rand.recipe_title}</span></div>
                      <img src="${randListThumbnail[status.index].thumbnail_path}"  width="200px" height="200px" />
                    </div>
                    <div class="panel-body">
                        <h4>${rand.usr_id}</h4>
                        <p>${rand.recipe_title}</p>
                    </div>
                </div>
		</div>
	<c:if test="${status.index eq 3 or  status.index eq 7}">
	</div>
	</c:if>
	
	</c:forEach>
</div>
		  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			<span  aria-hidden="true"><i class="fa fa-chevron-circle-left"></i>
</span>
			<span class="sr-only">Previous</span>
		  </a>
		  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			<span  aria-hidden="true"><i class="fa fa-chevron-circle-right"></i>
</span>
			<span class="sr-only">Next</span>
		  </a>
	</div>
    </header>

</div>