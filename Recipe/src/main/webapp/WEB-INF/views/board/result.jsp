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
				<h2 id="menutxt">검색결과</h2>
			</header>
			<div class="container all"></div>
		</section>

	</div>
</div>
<script>
var cnt = 0;
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
                            '<h4>'+data.recipe[i].recipe_title+'</h4>'+
                            '<p>'+data.recipe[i].usr_id+'</p> </div> </div>'
                            )
            
        }
	}
    
	window.onload = function () {
		var readData = {"type":"${list.searchType}","word":"${list.keyword}","cnt":cnt,"action" : "search"};

    	$.ajax({
            url: "/recipe/board/result_allList",
            type: "POST",
            dataType: "json",
            data : readData,
            success: function (data) {
            	
            	drawRecipe(data);
                cnt += 20;
                console.log(cnt);

            },
            error: function () {
                alert("error");
            }
        });
    };

    window.onscroll = function (ev) {
    	
        if ((window.innerHeight + window.scrollY) > document.body.offsetHeight) {
    		var readData = {"type":"${list.searchType}","word":"${list.keyword}","cnt":cnt,"action" : "search"};

            $.ajax({
                url: "/recipe/board/result_allList",
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
    };
</script>