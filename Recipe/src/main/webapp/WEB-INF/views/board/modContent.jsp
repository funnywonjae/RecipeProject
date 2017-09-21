<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="//unpkg.com/bootstrap/dist/css/bootstrap.min.css">
<script type="text/javascript" src="/recipe/resources/ckeditor/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//unpkg.com/jquery/dist/jquery.min.js"></script>
<script src="//unpkg.com/bootstrap/dist/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input type="hidden" id="bno" value="${item.recipe_no}">


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
					<th class="text-center">삭제</th>
				</tr>
			</thead>
				
				<c:forEach items="${ml_no}" var="ml_no" varStatus="status">
			<input type="hidden" id="material_no" value="${ml_no}">
			<tbody>
			<thead>
				<tr class="${status.count}"style="font-size:20px">
					
					<th class="text-center">${mlDTO[status.index].cg_name}</th>
					<th class="text-center" id="material_name_${mlDTO[status.index].ml_name}">${mlDTO[status.index].ml_name}</th>
					<th class="text-center" id="material_cnt_${status.count}">
				
					<input type="text" id="material_list_cnt${status.count}" value="${ml_cnt[status.index]}"
					style="width:90%; float:left ;align:center; text-align: center;">
					
					<p style="align : left; font-size:20px ; float:right" >${mlDTO[status.index].ml_unit}</p></th>
					<th class="text-center" id="material_unit"${status.count }> <a href="javascript:material_del(${status.count},${ml_no})">삭제</a></th>
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

<form class="form-horizontal" role="form" id="editorForm" method="POST" action="javascript:thumbnailModal();">
    <div class="form-group">
        <div class="form-group">
            <div class="col-lg-16">
            	<input type="text" id = "title" value="${item.recipe_title}" style="font-size: 20px">
            	
            	<br>
					<br>
					<select id="job">
						<option value="">카테고리 선택.</option>
						<c:forEach items="${categories}" var="categories"
							varStatus="status">
							<c:if test="${categories.cg_grpid >= '20000'}">
							<option  value="${categories.cg_id}">${categories.cg_name}</option>
							
							</c:if>
						</c:forEach>
				</select>
            	
                <textarea name="ckeditor" id="ckeditor" >${item.recipe_content}</textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-8" align="right">
                <button type="submit" id="writeBtn" class="btn btn-default">저장</button>
            </div>
        </div>
    </div>
</form>

 <input type="button" id="thumbnailModalBtn" href="#" data-toggle="modal" 
 			data-target="#thumbnail-modal" >

<div class="modal fade" id="thumbnail-modal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;"data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-header">
		썸네일 등록
		<button type="button" id = "modalClose" class="close" data-dismiss="modal">&times;</button>
		</div>
		<div class="modal-body">
	  <form id="fileForm" action="fileUpload" method="post"
        enctype="multipart/form-data">
        <input type="file" id="fileUp" name="fileUp"/><br/><br/>
     
        <input type="button" value="전송하기" onClick="writeSubmit();">
    </form>
	


		
		</div>
	
		

	</div>
	</div>
</div>


<script>

var cnt=$("#countHidden").val()+1;
var materialList=$("#material_no").val().toString();
console.log(materialList);

window.onload = function () {
	$("#thumbnailModalBtn").hide();
	console.log("materialList"+materialList);
	console.log("bb"+cnt);

	
}

function materialSubmit(){
	
	var material_list_cnt="";
	for(var i=1 ; i<=cnt ; i++){
		
		console.log("#material_list_cnt"+$("#material_list_cnt"+i).val());
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
	}
	console.log("asdasd"+material_list_cnt);
	var materialData = {'ml_no' : materialList,'ml_cnt':material_list_cnt};
	
	 $.ajax({
	        type : 'post',
	        url : '/recipe/board/modMaterial',
	        data:materialData,
	        dataType: 'text',
			/* contentType: 'application/json; charset=utf-8', */
			
	        success : function(data) {
	            alert(data);
	            fileSubmit();
	        },
	        error : function(data) {
	            alert("에러다");
	            console.log(data);
	            console.log(data.status);
	        }
	    });
}

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
			'<th class="text-center">'+cg+'</th>'+
			'<th class="text-center" id="material_name_'+txt+'">'+txt+'</th>'+
			'<th class="text-center" id="material_cnt_'+cnt+'">'+
			'<input type="text" id="material_list_cnt'+cnt+'"style="width:90%; float:left ;align:center; text-align: center;">'+
			'<p style="align : left; font-size:20px ; float:right" >'+unit+'</p></th>'+
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


function writeSubmit() {
	console.log($("#fileUp").val());
	if($("#fileUp").val()!=""){
  	 var writeData = {'recipe_content':CKEDITOR.instances.ckeditor.getData(),'recipe_title':$("#title").val(),
  			 					'recipe_no' : $("#bno").val(),'cg_id':$("#job option:selected").val(),'cg_name':$("#job option:selected").text()};
  	 

  	 
    $.ajax({
        type : 'post',
        url : '/recipe/board/modWrite',
        data: JSON.stringify(writeData),
        dataType: 'text',
 		contentType: 'application/json; charset=utf-8',
 		
        success : function(data) {
        	
            materialSubmit();
        },
        error : function(data) {
            alert("글쓰기에 실패하였습니다.");
            console.log(data);
            console.log(data.status);
        }
    });
}   else{
	alert("썸네일을 등록하세요");
}
}

function fileSubmit() {
    var thumbnailData = new FormData($("#fileForm")[0]);
    $.ajax({
        type : 'post',
        url : '/recipe/board/modFileUpload',
        data : thumbnailData,
        processData : false,
        contentType : false,
        
        success : function(html) {
            $("#modalClose").click();
			window.location.href = "/recipe/";

        },
        error : function(error) {
            alert("파일 업로드에 실패하였습니다.");
            console.log(error);
            console.log(error.status);
        }
    });
}



function thumbnailModal(){
	if($("#title").val()==""){
		alert("타이틀을 입력하세요");
		$("#title").focus();
	}
	else if($("#ckeditor").val()==""){
		$("#ckeditor").focus();
	}else if(materialList==""){
		alert("재료를 입력하세요");
		$("#material_search").focus();
	}
	else{
	thumbnailModalBtn.click();
	}
	}

    $(function(){
         
    	 CKEDITOR.replace( 'ckeditor', {//해당 이름으로 된 textarea에 에디터를 적용
             width:'100%',
             height:'auto',
             filebrowserImageUploadUrl: '/recipe/community/imageUpload' //여기 경로로 파일을 전달하여 업로드 시킨다.
         });
         
        CKEDITOR.on('dialogDefinition', function( ev ){
            var dialogName = ev.data.name;
            var dialogDefinition = ev.data.definition;
          
            switch (dialogName) {
                case 'image': //Image Properties dialog
                    //dialogDefinition.removeContents('info');
                    dialogDefinition.removeContents('Link');
                    dialogDefinition.removeContents('advanced');
                    break;
            }
        });
        //버튼 클릭시 이미지파일은 ajax로 전송한다.
    
    });
   </script>
