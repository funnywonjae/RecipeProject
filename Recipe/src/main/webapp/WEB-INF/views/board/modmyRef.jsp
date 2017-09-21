<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="/recipe/resources/recipe/css/write_Material_css.css">

<link rel="stylesheet"
	href="//unpkg.com/bootstrap/dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="/recipe/resources/ckeditor/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//unpkg.com/jquery/dist/jquery.min.js"></script>
<script src="//unpkg.com/bootstrap/dist/js/bootstrap.min.js"></script>
<br>
<br>
<form id="frm" method="post">
		<div align="right">
			<br />
	
			<input id="modBtn" type="button" value="수정">
 
			<input id="delBtn" type="button" value="삭제">
	
	</div> 
</form>	

<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div align="center">
	
				 <input
					type="text" id="material_search" placeholder="Search"
					style="width: 200px"> <br>
				<p>재료를 검색후 선택해 주세요.</p>
		<div class="search"
					style="padding: 10px; overflow: auto; border: 1px solid gold; width: 800px; height: 300px;"></div>
				<div class="material_list"></div>
			</div>
			<br>
		</div>
		
		<table class="table table-bordered table-hover" id="tab_logic">
		
			<thead>
				<tr  style="font-size:20px">
					<th class="text-center">#</th>
					<th class="text-center">재료</th>
					<th class="text-center">갯수</th>
					<th class="text-center">유통기한</th>
					<th class="text-center">삭제</th>
				</tr>
			</thead>
			<input type="hidden" id="material_no" value="${my_mlno}">
			
				<c:forEach items="${my_ml_no}" var="ml_no" varStatus="status">
			
			<tbody>
			<thead>
				<tr class="${status.count}"style="font-size:20px">
					<th class="text-center">${status.count}</th>
					<th class="text-center" id="material_name_${mlDTO[status.index].ml_name}">${mlDTO[status.index].ml_name} (${mlDTO[status.index].cg_name})</th>
					
					<th class="text-center" id="material_cnt_${status.count}">
					<input type="text" id="material_list_cnt${status.count}" value="${my_ml_cnt[status.index]}"
					style="width:90%; float:left ;align:center; text-align: center;">
					
					<p style="align : left; font-size:20px ; float:right" >${mlDTO[status.index].ml_unit}</p></th>
					
					<th class="text-center" id="material_edate_${status.count}">
					<input type="text" id="material_edate${status.count}" value="${my_ml_edate[status.index]}"
					style="width:90%; float:left ;align:center; text-align: center;">
					<p style="align : left; font-size:20px ; float:right" >까지</p></th>
					
					<th class="text-center" id="material_unit"${status.count}> <a href="javascript:material_del(${status.count},${ml_no})">삭제</a></th>
			</tr>
			</thead>
			
			<c:if test="${status.last}">
			<input type="hidden" id="countHidden" value="${status.count}"> </c:if>
						</c:forEach>
						
			</tbody>
			
			
			<tbody class="material_list_body">
			</tbody>
			
		</table>
		
	</div>
	
</div>
</form>
<script>
var cnt=(1*$("#countHidden").val())+1;
var materialList=$("#material_no").val().toString();

console.log(materialList);

function materialSubmit(){
	console.log(materialList);
	var material_list_cnt="";
	var material_edate="";
	for(var i=1 ; i<=cnt ; i++){
		
		console.log("#material_list_cnt"+$("#material_list_cnt"+i).val());
		console.log("#material_edate"+$("#material_edate"+i).val());
		
		if($("#material_list_cnt"+i).length==1){

		if(material_list_cnt==""){
		console.log("ddsa"+material_list_cnt);
		material_list_cnt=$("#material_list_cnt"+i).val();
		console.log("bbb"+material_list_cnt);
		}
		else{
			material_list_cnt+=","+$("#material_list_cnt"+i).val();
			console.log("ccc"+material_list_cnt);
		}
		}
		
		if($("#material_edate"+i).length==1){

			if(material_edate==""){
			console.log("ddsa"+material_edate);
			material_edate=$("#material_edate"+i).val();
			console.log("bbb"+material_edate);
			}
			else{
				material_edate+=","+$("#material_edate"+i).val();
				console.log("ccc"+material_edate);
			}
			}
	}
	console.log("list"+materialList);
	console.log("cnt"+material_list_cnt);
	console.log("edate"+material_edate);
	var materialData = {'ml_no' : materialList,'ml_cnt':material_list_cnt,'ml_edate':material_edate};
	 $.ajax({
	        type : 'post',
	        url : '/recipe/board/ref_update',
	        data:materialData,
	        dataType: 'text',
			/* contentType: 'application/json; charset=utf-8', */
			
	        success : function(data) {
	            alert(data);
	           
	        },
	        error : function(data) {
	            alert("에러다");
	            console.log(data);
	            console.log(data.status);
	        }
	    });}



function material_del(cnt,no){
	var a = new Array();
	$("."+cnt).empty();
	a = materialList.split(",");
	materialList="";
	$.each(a, function(i, d) {
		console.log("no = ",no);
		if (d!=no) {
			console.log("asd" + d);
			
			if (materialList == "") {
				materialList = d;

			} else {
				materialList += "," + d;
			}
		}
		console.log("materialList = "+materialList);

	});
	
}
function material_add(txt,unit,cg,no){

if(($('#material_name_'+txt)).length==1){
	alert("이미 추가된 재료입니다.");
}else{
	$(".material_list_body").append(
			' <tr class="'+cnt+'" style="font-size:120%">'+
			'<th class="text-center">'+cnt+'</th>'+
			
			'<th class="text-center" id="material_name_'+txt+'">'+cg+"("+txt+")"+'</th>'+
				
			'<th class="text-center" id="material_cnt_'+cnt+'">'+
			'<input type="text" id="material_list_cnt'+cnt+'"style="width:90%; float:left ;align:center; text-align: center;">'+
			'<p style="align : left; font-size:20px ; float:right" >'+unit+'</p></th>'+
			
			'<th class="text-center" id="material_edate_'+cnt+'">'+
			'<input type="text" id="material_edate'+cnt+'"style="width:90%; float:left ;align:center; text-align: center;">'+
			'<p style="align : left; font-size:20px ; float:right" >까지</p></th>'+
			
			'<th class="text-center" id="material_unit'+cnt+'"><a href="javascript:material_del('+cnt+','+no+')">삭제</a></th>'+
		'</tr>'
	 );


	
	if(materialList==""){
		materialList=no;

	}else{
		materialList+=","+no;
	}
	 cnt++;	


	}

$("#material_search").val("");
$(".search").empty();
}

$("#material_search").keyup(function() {
					var word = {"word" : $("#material_search").val()}
					$.ajax({
								type : 'post',
								url : '/recipe/board/material_search',
								data : word,
								dataType : "json",
								success : function(data) {
									$(".search").empty();
									console.log(data);

										$.each(data, function(i, d){
											$(".search").append(
													'<div class="col-md-3 col-sm-6"  style="cursor:pointer">'+
													"<p onclick='material_add("+'"'+d.ml_name+'"'+',"'+d.ml_unit+'"'+',"'+d.cg_name+'"'+',"'+d.ml_no+'"'+")'>"+ d.ml_name+"("+d.cg_name+")" +"</p></div>"
											);
										});
									},
								error : function(data) {
									console.log("없습니다.");
								}
							});
});
function refdelete(){
	
}
$("#modBtn").click(function() {
	alert("재료 변경");
	materialSubmit();

});
$("#delBtn").click(function() {
	$("#frm").attr("action", "/recipe/board/ref_delete");
	$("#frm").submit();

});
</script>



