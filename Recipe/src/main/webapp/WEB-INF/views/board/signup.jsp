<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<meta charset="utf-8" />
<head>

<title>MY R.E.F 회원가입 페이지</title>

<link rel="stylesheet" href="/recipe/resources/assets/css/signup.css" />
</head>
<body>

   <section class="container">
      <article class="half">
         <h1>이정수's 아이s</h1>
         <div class="tabs">
            <span class="tab signin active"><a href="#signin">Sign in</a></span>
            <span class="tab signup"><a href="#signup">Sign up</a></span>
         </div>
         <div class="content">
            <div class="signin-cont cont">

               <form action="javascript:loginCheck(this);" method="post" enctype="multipart/form-data">
                  <input type="email" name="loginUsr_id" id="loginUsr_id" class="inpt" required="required"  
                  placeholder="Email Address" value = ${ sessionScope.saveId }>
                  <label for="email">Your email</label>
                  <input type="password" name="usr_pwd" id="usr_pwd" class="inpt" required="required" placeholder="Pass Word" >
                      <label for="password">Your password</label>
                  <input type="checkbox" id="remember" name="remember" class="checkbox" checked="checked"><label for="remember">remember</label>
                     
                     <div class="submit-wrap">   
                     <input type="submit" id="login" name="login" value="Sign in" class="submit">
                     
                        <!-- 원래 비번찾기<a href="#" class="more">Forgot your password?</a> -->
                        <a><button type="button" class="more" data-toggle="modal" data-target=".bs-example-modal-sm">Forgot your password?</button></a>
                        
               </form>      
                     </div>
                        <!-- 이건 모달? 비번찾기 모달 -->
                        <div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                          <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                              <center><strong>비번 찾기</strong></center>
                              <form action="javascript:findPw(this);" method="post" name="frmPw" id="frmPw" enctype="multipart/form-data">   
                              <input type="email" id=emailToFind name="emailToFind" class="inpt" placeholder="Your Email">
                              <input type="text" id="nameToFind" name="nameToFind" class="inpt" placeholder="Your Name">
                              
                              <center><input id="hiddenPw" value="비밀번호 : " readonly="readonly"></center>
                              
                              <center><input type="submit" id="findBtn" name="findBtn" class="fBtn" value="찾기"></center>                              
                              
                                <div class="modal-footer">
                                     <button type="button" class="btn btn-default" data-dismiss="modal" id="modalClose">CLOSE</button>
                                    </div>
                                    
                              </form>
                            </div>
                          </div>
                        </div>

            </div>
            <div class="signup-cont cont">
               <form action="javascript:check(this);">

                  <div>
                     <input type="email" name="usr_id" id="usr_id" class="inpt"
                        required="required" placeholder="이메일"> 
                        <button type="button" id="checkid" >Email중복체크</button>
                        <button type="button" id="checkname" >닉네임중복체크</button>
                     <input type="text" name="text" id="usr_name" class="inpt"
                        required="required" placeholder="아이디" maxlength="30">
                  </div>
                  <div>
                     <input type="password" name="password" id="usr_password"
                        class="inpt" required="required" placeholder="비밀번호" size="20">
                     <input type="password" name="passwordchk" id="usr_passwordchk"
                        class="inpt" required="required" placeholder="비밀번호확인" size="20">
                  </div>
                  <div id="checkpwd" style="color: red"></div>

                  <div class="submit-wrap">
                     <input type="submit" id="signup_btn" value="Sign up"
                        class="submit"> <a href="#" class="more">Terms and
                        conditions</a>
                  </div>
               </form>

            </div>
         </div>
      </article>
      <div class="half bg"></div>
   </section>



   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

   <!-- 로그인 버튼 클릭 -->
   <script type="text/javascript">
      function loginCheck(object){
         
         
         //아이디저장 체크버튼을 위한 변수저장
         var ri = {"check":$("#remember").is(':checked')};

         //로그인을 위한 id, pwd를 변수에 저장
         var loginData = {
            
            usr_id : $("#loginUsr_id").val(),   
            usr_pwd : $("#usr_pwd").val(),
            
               
         };
         console.log(loginData);
          
       
         console.log(ri);
         
          if(ri){
             console.log("체크됨");
         // remember의 체크값을 controller로 이동시키기 위한.
             login(loginData);
             checkbox(ri);
             
          }
          else{
             console.log("체크안됨");
             login(loginData);
             checkbox(ri);
          }   
   
      }
      
/* ajax로 usr_id, usr_pwd 값 전달 */   
   function login(loginData){
         
      $.ajax({
         
         url : "/recipe/board/loginUser",
         type : "POST",
         contentType : "application/json;charset=UTF-8",
         data : JSON.stringify(loginData),
         dataType : "json",
         success : function(data) {
            alert("로그인성공");
            window.location.href = "/recipe/?page=main";

            
         },
         error : function(data){
            alert("로그인실패");
         }
      });
      
   }
/* 체크박스의 체크값을 전달 */
   function checkbox(ri){

      console.log("ajax에 들어가기전 " + ri);
      
      $.ajax({
         url : "/recipe/board/checkbox",
         type : "POST",
         data : ri,
         
         success : function(data){
            console.log("checkbox 대성공");
         },
         error : function(data){
            console.log("checkbox 실패");
            
         }
         
      });
      
   } 
   </script>
   

   
   <!-- 비번찾기 버튼 클릭시 -->
   <script type="text/javascript">
   
   <!-- 비번체크 숨기기 -->
   $(document).ready(function(){
   
      $("#hiddenPw").hide();
   
   });
   // 찾을 비번에 해당하는 이메일과 이름을 저장
      function findPw(object){
         
         var dataToFind = {
               
               usr_id : $("#emailToFind").val(),
               usr_name : $("#nameToFind").val()
               
         }
               
         console.log(dataToFind);
         findUser(dataToFind);
         
         
                  
      }
      
      //findPw에서 넘겨받은 값으로 비번찾기
      function findUser(dataToFind){

         $.ajax({
            
            url : "/recipe/board/findPw",
            type : "POST",
            contentType : "application/json;charset=UTF-8",
            data : JSON.stringify(dataToFind),
            dataType : "json",
            success : function(data) {
               $("#hiddenPw").val("비밀번호 : ");

               $("#hiddenPw").show();
               console.log(data.usr_pwd);
               $("#hiddenPw").val($("#hiddenPw").val()+data.usr_pwd);
               
               
            },
            error : function(data) {
               $("#hiddenPw").val("비밀번호 : ");
               alert("비번찾기 실패");
            }
            
         });
         
      }
      
      // 모달 close시 모달창 리셋
      $("#modalClose").click(function(){
         document.frmPw.reset();
         $("#hiddenPw").hide();
      });
   
   </script>
   
      
   
   
   
   
   
   

   
   
   <script type="text/javascript">
   //UserInfoDTO usr_id에 값 입력 
   $("#checkid").click(function(){
      var chkidData = {
            usr_id : $("#usr_id").val()
      };
      console.log(chkidData);
      chkusr_id(chkidData);
   });
   
   //UserInfoDTO usr_name에 값 입력 
   $("#checkname").click(function(){
      var chknameData = {
            usr_name : $("#usr_name").val()
      };
      
      console.log(chknameData);
      chkusr_name(chknameData);
   });
   
   //아이디중복체크 
    function chkusr_id(chkidData){
     $.ajax({
      url : "/recipe/board/signup/chkusr_id",
      type : "post",
      data : JSON.stringify(chkidData),
      contentType : "application/json;charset=UTF-8",
      dataType : "json",
      success : function(data){
       
         alert(data.resultMsg);
      }
           });
    }
   //닉네임 중복체크 
    function chkusr_name(chknameData){
     $.ajax({
      url : "/recipe/board/signup/chkusr_name",
      type : "post",
      data : JSON.stringify(chknameData),
      contentType : "application/json;charset=UTF-8",
      dataType : "json",
      success : function(data){
       
       alert(data.resultMsg);

       
      }
           });
    }
      //비밀번호 체크 jqurey
      $(document).ready(function() {
      $('#usr_passwordchk').keyup(function() {
         if ($('#usr_password').val() != $('#usr_passwordchk').val()) {
            $('#checkpwd').text(''); //제거   
            $('#checkpwd').text("암호를 정확히 입력해주세요.");
         } else {
            $('#checkpwd').text('');
            $('#checkpwd').text(" ");
         }
      }); 
   });   

    //회원가입 Ajax 전송 
    //usr_name, usr_id, usr_pw 모두 입력되었는지 체크후 ajax로 전송 
   function check(object) {
         var signup_Data = {
               usr_id : $("#usr_id").val(),
               usr_name : $("#usr_name").val(),
               usr_pwd : $("#usr_password").val()
         };
         console.log(signup_Data);
         signup(signup_Data);
      
   }
   //ajax로 check값 받은후 해당 쿼리가 성공시 alert 가입성공 메시지후 로그인 페이지로 이동 
   function signup(signup_Data) {
      console.log(signup_Data);
      $.ajax({
         url : "/recipe/board/signup",
         type : "POST",
         contentType : "application/json;charset=UTF-8",
         data : JSON.stringify(signup_Data),
         dataType : "json",

         success : function(data) {
            alert("가입 성공");
            
            window.location.href = "/recipe/?page=signup";
            // 페이지 이동 할때 루트경로(/recipe)+page값으로 전송한다.
            //->>>>staticController로 이동
         },
         error : function(data) {
            alert("가입 실패");
         }

      });
   } 

   $('.tabs .tab').click(function() {
      if ($(this).hasClass('signin')) {
         $('.tabs .tab').removeClass('active');
         $(this).addClass('active');
         $('.cont').hide();
         $('.signin-cont').show();
      }
      if ($(this).hasClass('signup')) {
         $('.tabs .tab').removeClass('active');
         $(this).addClass('active');
         $('.cont').hide();
         $('.signup-cont').show();
      }
   });
   $('.container .bg').mousemove(
         function(e) {
            var amountMovedX = (e.pageX * -1 / 30);
            var amountMovedY = (e.pageY * -1 / 9);
            $(this).css('background-position',
                  amountMovedX + 'px ' + amountMovedY + 'px');
         });
</script>
</body>
</html>