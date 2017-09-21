<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--정수정수이정수와 아이들 - 김원재 -->

<c:set var="usr_id" value="${userInfo}" />
<c:set var="recipeItem" value="${item}" />
<%-- <c:set var="bookmarklist" value ="${bookmarklist }"/>
 --%>
<%-- <c:set var="ml_no" value="${ml_no}"/>
<c:set var="ml_cnt" value="${ml_cnt}"/> --%>
<form id="frm" method="post">
	<div align="right" class="readBtn">

		<br />
			<c:forEach items="${list}" var="list" varStatus="status">
			<c:if test="${list eq recipeItem.recipe_no}">
			<input type="hidden" id="listhidden" name="listhidden" value="${list}">
			
			</c:if>
</c:forEach>

		<c:if test="${usr_id.usr_id eq recipeItem.usr_id}">
			<input id="modBtn" type="button" value="수정">
			<input id="delBtn" type="button" value="삭제">


		</c:if>

	</div>
	<br> <br>



	<div align="center" style="word-wrap: break-word">


		<input type="hidden" name="bno" id="bno"
			value="${recipeItem.recipe_no}">
		<h1>
			<c:out value="${recipeItem.recipe_title}" />
		</h1>
		<table class="table table-bordered table-hover" id="tab_logic">
			<thead>
				<tr>
					<th class="text-center">#</th>
					<th class="text-center">재료</th>
					<th class="text-center">갯수</th>
					<th class="text-center">내가 가진 갯수</th>
					<th class="text-center">단위</th>
				</tr>

			</thead>
			<c:forEach items="${ml_no}" var="ml_no" varStatus="status">

				<tbody>
				<thead>
					<tr id = "tr${status.count}" >
		
						<th class="text-center">${status.count}</th>
						<th class="text-center">${mlDTO[status.index].ml_name}</th>
						
						
						<th class="text-center" id="recipe${status.count}">${ml_cnt[status.index]}</th>
				
						
						<th class="text-center" id = "compare${status.count}">${compare[status.index]}</th>
						
						<th class="text-center">${mlDTO[status.index].ml_unit}</th>
					</tr>
				</thead>
			</c:forEach>

			</tbody>


		</table>

		<h3>
			<c:out value="작성자 : ${recipeItem.usr_id}" />
		</h3>
		<div style="min-height: 800px; border: 1px solid gainsboro; ">
		<c:out value="${recipeItem.recipe_content}" escapeXml="false" />
		</div>
	</div>
	<br><br>

	<div>
	<%@include file="comment.jsp" %>
	</div>

</form>

<script>
//수정버튼
$("#modBtn").click(function() {
	alert("썸네일 변경은 글쓰기 버튼을 누르시면 가능합니다.");
	$("#frm").attr("action", "/recipe/board/modContent");
	$("#frm").submit();
});

//삭제버튼
$("#delBtn").click(function() {
	$("#frm").attr("action", "/recipe/board/delContent");
	$("#frm").submit();
});

//.readBtn에 즐겨찾기 추가/해제 버튼을 추가한다.

function bookmarkBtn(val){
	$(".readBtn").empty();
	if(val=="del"){
	$(".readBtn").append(
		'<input id="bookmark_delBtn" type="button" value="즐겨찾기 해제" onclick="bookmark_del()">' 
		);
	}
	else{
		$(".readBtn").append(
			'<input id="bookmark_addBtn" type="button" value="즐겨찾기 추가" onclick="bookmark_add()">' 
		);
	}
}


function bookmark_del(){
	bookmark('/recipe/board/bookmark_del');	
}

function bookmark_add(){
	bookmark('/recipe/board/bookmark_add');

}

window.onload = function () {
	
	//레시피 재료와 나의 냉장고속 레시피를 비교한다.
	//부족할경우에는 해당 재료칸의 background-color가 변한다.
	
	for(var i = 1 ; $("#tr"+i).length;i++){
		var recipe = Number($("#recipe"+i).text());
		var compare = $("#compare"+i).text();
		
		if((recipe>Number(compare)) ||(typeof Number(compare)!="number")){
 			$("#tr"+i).attr("style","background-color: palevioletred");
		}	
	}

	//본인이 쓴 글이 아닐경우에만 즐겨찾기 버튼이 나타난다.
	
	if("${usr_id.usr_id}"!=''){
	 if("${usr_id.usr_id}"!=("${recipeItem.usr_id}")){
		if($("#bno").val()==$("#listhidden").val()){
					bookmarkBtn("del");
		}	
	else{
				bookmarkBtn("add");
	}
	 }
	}
}
//버튼을 눌렀을때 ajax통신
function bookmark(url){

	var bookmarkData = {"bno" : $("#bno").val()} 
	$.ajax({
		type : 'post',
		url : url,
		data : bookmarkData,
		success : function(data) {
			if($("#bookmark_delBtn").length){
					bookmarkBtn("add");
			}
			else{
						bookmarkBtn("del");
			}
		},
		error : function(data) {
			alert("오류 발생 ");
			console.log(data);
		}
	});
}
		

</script>

