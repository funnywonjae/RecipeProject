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
		<section class="randRecipes">
			<header class="major">
				<h2>추천 메뉴</h2>
			</header>
			<div class="features">
				<article>
					<div class="content rand">
						<%@include file="main_randRecipe.jsp"%>
					</div>
				</article>
			</div>
		</section>

		<section>
			<header class="major">
				<h2 id="menutxt">전체메뉴</h2>
			</header>
			<div class="container all"></div>
		</section>

	</div>
</div>
<script>
var readData;
var cnt = 0;
		       
	function getReadData(){
    	  $(".randRecipes").show();
			var category;
			
	    	if("${category}"==null){
				category="";
			}
	    	else category="${category}";
	         readData = {"cnt" : cnt,"category" : category,"action" : "main"};
	    	}
	
    window.onload = function () {
    	getReadData();
    	$.ajax({
            url: "/recipe/board/allList",
            type: "POST",
            dataType: "json",
            data : readData,
            
            success: function (data) {
            	
            	drawRecipe(data);
   		  	   cnt += 20;

            },
            error: function () {
                alert("error");
            }
        });
    };

    window.onscroll = function (ev) {
    	getReadData();
		if(cnt>=20){
    	if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            $.ajax({
                url: "/recipe/board/allList",
                type: "POST",
                data : readData,
                dataType: "json",
                success: function (data) {
                	drawRecipe(data);
	    		     cnt += 20;

                },
                error: function () {
          alert("에러");
                }
            });
        }
		}
    };
    function drawRecipe(data){
		for (i in data.recipe) {
            $(".all").append(                  		
        			'<div class="col-md-3 col-sm-6" style="cursor:pointer" onclick='+
        			'location.href="/recipe/?no='+data.recipe[i].recipe_no+'&page=readContent">'+

                    '<div class="panel panel-default text-center">'+
                        '<div class="panel-heading">'+
    					'<div class="col-img-responsive02"><span>"'+data.recipe[i].recipe_title+'"</span></div>'+
                         '<img src="'+data.thumbnail[i].thumbnail_path+'"  width="200px" height="200px" /></div>'+
                        '<div class="panel-body">'+
                            '<h4>'+data.recipe[i].usr_id+'</h4>'+
                            '<p>'+data.recipe[i].recipe_title+'</p> </div> </div>'
                            )
            
        }
}


</script>