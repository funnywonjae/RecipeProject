<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 서원준  -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment Page~!!!!!</title>
</head>
<body>

	<form id="form1" name="form1">


		<div class="replyList">
			<%-- <c:set var="replyList" value="${comment}" /> --%>
			<c:set var="replyItem" value="${item}" />
			<div id="replyItem">
				전체 코멘트
				<!-- 글 댓글 수 출력  -->
				<div id="replyList">


					<table cellspacing='0' class="commentTable">
						<thead>
							<tr>
								<th>작성자</th>
								<th>코멘트 내용</th>
								<th>작성일</th>
								<th>수정</th>
								<th>삭제</th>
							</tr>
						</thead>
						<!-- Table Header -->
						<tbody>
						</tbody>
						<c:forEach var="replylist" items="${comment}" varStatus="status">
							<tbody class=${replylist.COM_INDEX}>
								<tr>
									<!-- 작성자 아이디 -->
									<td>${replylist.USR_ID}</td>
									<!-- 댓글 내용 -->
									<td class="commenttext" id="commenttext${replylist.COM_INDEX}" >${replylist.COM_CONTENT}</td>
									<!-- 댓글 작성일 -->
									<td>${ replylist.COM_WRITEDATE}</td>


									<!-- 댓글 수정 -->
									<c:if test="${usr_id.usr_id eq replylist.USR_ID}">
									<td>
										<button type="button" id="commentmodbtn${replylist.COM_INDEX}" onclick="modcommen(this.value)"
											value=${replylist.COM_INDEX}>수정</button>
									</td>
									<!-- 댓글 삭제 -->
									<td>
										<button type="button" onclick="delComment(this.value)"
											id="comment_delete" value=${replylist.COM_INDEX}>삭제</button> 
									</td>
									</c:if>
									<c:if test="${usr_id.usr_id ne replylist.USR_ID}">
										<td></td>
										<td></td>
									</c:if>
								</tr>
								<!-- 댓글수정내용  --> 
								<tr>
									<td id="commentmod${replylist.COM_INDEX}"  style="display:none;">
										<textarea rows="4" cols="50" id="modComment${replylist.COM_INDEX}" name="modComment${replylist.COM_INDEX}" style="FONT-SIZE: 9pt"   >${replylist.COM_CONTENT}</textarea> 
										<button type="button" onclick="modcommen(this.value)" value=${replylist.COM_INDEX}>전송</button>
										<button type="button"  id="modcommentbtn1${replylist.COM_INDEX}" >취소</button>
									</td>
								</tr>
								
							</tbody>
						</c:forEach>
					</table>

				</div>





				<ul>

					<input type="hidden" id="recipe_no"
						value="<c:out value="${item.recipe_no}"/>">
					<label for="commentParentName">아이디</label>
					<input type="text" id="commentParentName" disabled="disabled"
						value="<c:out value="${usr_id.usr_id }"/>">
				</ul>
			</div>
			<div style="margin: 0; padding: 0;">


				댓글 내용
				<textarea id="commentParentText" style="FONT-SIZE: 9pt"
					name="commentParentText" onkeyup="updateChar(100);" rows="4"
					cols="50"></textarea>
				<input type="button" id="re_write" value="등록"
					class="btn btn-default btn-lg btn-danger"> <br> 현재 <span
					id="textlimit">0</span> /최대 100byte(한글 50자, 영문 100자)

			</div>

		</div>



	</form>

	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">
	
	
	//코멘트 내용 
	function modcommen(value) {
		
		
		var modco = "#commentmodbtn"+value
		var modshow = "#commentmod"+value
		var modcanclebtn = "#modcommentbtn1"+value
		var modtext = "#modComment"+value
		var origintext = "#commenttext"+value
		
		$(document).ready(function(){
	    $(modco).click(function(){
	        $(modshow).toggle();
	    });
		});
	
	$(document).ready(function(){
	    $(modcanclebtn).click(function(){
	        $(modshow).toggle();
	    });
	});	
	
	var modData = {
			com_index : value,
		recipe_no : $("#recipe_no").val(),
		com_content : $(modtext).val()
	}
	$.ajax({
		url : "/recipe/board/modcomment",
		type : "post",
		data : JSON.stringify(modData),
		contentType : "application/json;charset=UTF-8",
		dataType : "text",
		success : function(data) {
			if ($("." + value).length == 1) {
				$("." + value).val("");
				$(modshow).toggle();
				$(origintext).text(modData.com_content); 
			}

		}
	});
	
	}
	//코멘트 삭제 스크립트  
		function delComment(value) {
			var delData = {
				com_index : value,
				recipe_no : $("#recipe_no").val()
			}
	
	
			$.ajax({
				url : "/recipe/board/delcomment",
				type : "post",
				data : JSON.stringify(delData),
				contentType : "application/json;charset=UTF-8",
				dataType : "text",
				success : function(data) {
					if ($("." + value).length == 1) {
						$("." + value).empty();
					}
	
				}
			});
		}

	
		//코멘트 내용 제한 스크립트 
		function updateChar(length_limit) {
			var form = document.bbsForm;
			var length = calculate_msglen(document.getElementById('commentParentText').value);
			textlimit.innerText = length;
			if (length > length_limit) {
				alert("최대 " + length_limit + "byte이므로 초과된 글자수는 자동으로 삭제됩니다.");
				form1.commentParentText.value = document.getElementById('commentParentText').value.replace(/\r\n$/, "");
				form1.commentParentText.value = assert_msglen(document.getElementById('commentParentText').value, length_limit);
			}
		}

		function calculate_msglen(message) {
			var nbytes = 0;
	
			for (i = 0; i < message.length; i++) {
				var ch = message.charAt(i);
				if (escape(ch).length > 4) {
					nbytes += 2;
				} else if (ch == '\n') {
					if (message.charAt(i - 1) != '\r') {
						nbytes += 1;
					}
				} else if (ch == '<' || ch == '>') {
					nbytes += 4;
				} else {
					nbytes += 1;
				}
			}
			return nbytes;
		}
		function assert_msglen(message, maximum) {
			var inc = 0;
			var nbytes = 0;
			var msg = "";
			var msglen = message.length;
	
			for (i = 0; i < msglen; i++) {
				var ch = message.charAt(i);
				if (escape(ch).length > 4) {
					inc = 2;
				} else if (ch == '\n') {
					if (message.charAt(i - 1) != '\r') {
						inc = 1;
					}
				} else if (ch == '<' || ch == '>') {
					inc = 4;
				} else {
					inc = 1;
				}
				if ((nbytes + inc) > maximum) {
					break;
				}
				nbytes += inc;
				msg += ch;
			}
			textlimit.innerText = nbytes;
			return msg;
		}
	
		//commnet 댓글 내용입력 (유저 id, 코멘트 내용, 레시피 번호, 코멘트 번호)
		$("#re_write").click(function() {
			var comData = {
				usr_id : $("#commentParentName").val(),
				com_content : $("#commentParentText").val(),
				recipe_no : $("#recipe_no").val()
			};
			commentupload(comData);
	
	
		});
		
		//ajax 코멘트 댓글 전송 성공시 갱신된댓글 리스트 출력 
		function commentupload(comData) {
			var str='';
			$.ajax({
				url : "/recipe/board/comment",
				type : "post",
				data : JSON.stringify(comData),
				contentType : "application/json;charset=UTF-8",
				dataType : "json",
				success : function(data) {
					//현재 날짜 
					var today = new Date();
					var dd = today.getDate();
					var mm = today.getMonth() + 1; //January is 0!
					var yyyy = today.getFullYear();
					if (dd < 10) {
						dd = '0' + dd
					}
					if (mm < 10) {
						mm = '0' + mm
					}
					today = yyyy + '-' + mm + '-' + dd;
						
					
					str+='<tbody class=' + data.com_index + '> <tr>';
					
					/* 작성자 아이디  */
					str+='<td>' + $("#commentParentName").val() + '</td>';
					 /* 댓글 내용 */
					str+='<td>' + $("#commentParentText").val() + '</td>';
					/* 댓글 작성일 */
					str+='<td>' + today + '</td>' ;
					
					if("${usr_id.usr_id}" == data.usr_id){
						 /* 댓글 수정 */ 
						str+='<td><button type="button" onclick="modcommen(this.value)" value=' + data.com_index + '>수정</button></td>';
						/* 댓글 삭제 */
						str+='<td> <button type="button" onclick="delComment(this.value)"';
						str+='id= "comment_delete" value=' + data.com_index + '>삭제</button>	</td>';
						str+='</tr> </tbody>';
					}else{
						str+='<td></td><td></td>';
						str+='</tr> </tbody>';
					}
					$(".commentTable").append(
						str
					);
					$("#commentParentText").val("");
					location.reload();
				}
			});
		}
	</script>



</body>
</html>