package com.dj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.domain.UserInfoDTO;
import com.dj.service.UserInfoService;

// TODO: Auto-generated Javadoc
/**
 * The Class SignupController.
 */
@Controller
public class SignupController {

   /** The user info service. */
   @Inject UserInfoService userInfoService;
   
   
   /** The Constant logger. */
   private static final Logger logger = 
         LoggerFactory.getLogger(SignupController.class);

   /**
    * Login user.
    *
    * @param dto the dto
    * @param httpSession the http session
    * @param request the request
    * @param response the response
    * @return the response entity
    * @throws Exception the exception
    */
   // 로그인
   @RequestMapping(value = "/board/loginUser", method = RequestMethod.POST)
   public ResponseEntity<UserInfoDTO> loginUser(@RequestBody UserInfoDTO dto, HttpSession httpSession, HttpServletRequest request,
         HttpServletResponse response) throws Exception {
      ResponseEntity<UserInfoDTO> entity = null;

      boolean result = userInfoService.loginUser(dto, httpSession, request, response);

      if (result) {
         entity = new ResponseEntity<UserInfoDTO>(dto, HttpStatus.OK);
         
         HttpSession session = request.getSession();
         String saveId = dto.getUsr_id();
/*         session.setAttribute("saveId", saveId);*/
         

      } else {
         entity = new ResponseEntity<UserInfoDTO>(dto, HttpStatus.BAD_REQUEST);
      }

      return entity;
   }
   
   /**
    * Logout.
    *
    * @param httpSession the http session
    * @param model the model
    * @return the string
    * @throws Exception the exception
    */
   // 로그아웃
   @RequestMapping(value = "/board/logout", method = RequestMethod.GET)
   public String logout(HttpSession httpSession, Model model) throws Exception {
      System.out.println("로그아웃으로 왔음");
      userInfoService.logout(httpSession);

      // 로그아웃하고 StaticController로 이동함.
      return "forward:/";

   }
   
   /**
    * Checkbox.
    *
    * @param ri the ri
    * @param request the request
    * @param response the response
    * @throws Exception the exception
    */
   // 체크박스 상태에 따라 세션에 아이디 저장하거나 안함
   @RequestMapping(value="/board/checkbox", method = RequestMethod.POST)
   public void checkbox(@RequestParam(required=true, value="check") boolean ri,
         HttpServletRequest request, HttpServletResponse response) throws Exception{
      
      
      HttpSession session = request.getSession();
      
      String cookieId = (String)session.getAttribute("saveId");
      session.setAttribute("CookieId", cookieId);      
      logger.info(""+ri);
      if( ri ){
         Cookie c = new Cookie("cookieId",cookieId);
         c.setMaxAge(60*60*24);
         response.addCookie(c);   
      }else{   
         session.removeAttribute("saveId");
         Cookie c = new Cookie("cookieId",cookieId);
         c.setMaxAge(0);
         logger.info("아 뭐 좀 시발 되라"+(String)session.getAttribute("saveId"));

         response.addCookie(c);
      }
      
      
   }
   
   /**
    * Find pw.
    *
    * @param dto the dto
    * @param request the request
    * @return the response entity
    * @throws Exception the exception
    */
   //비번 찾기
   @RequestMapping(value = "/board/findPw", method = RequestMethod.POST)
   public ResponseEntity<UserInfoDTO> findPw(@RequestBody UserInfoDTO dto, HttpServletRequest request) throws Exception {
      
      logger.info(""+dto);
      ResponseEntity<UserInfoDTO> entity = null;

      boolean result = userInfoService.findPw(dto);
      logger.info(""+result);
      if (result) {
         
         logger.info("비번찾기 if"+dto);
         dto.setUsr_pwd(userInfoService.rePw(dto));
         logger.info("비번이 나오는 dto : "+dto.getUsr_pwd());
         
         HttpSession session = request.getSession();
         entity = new ResponseEntity<UserInfoDTO>(dto, HttpStatus.OK);
/*         session.setAttribute("dtoPw", dtoPw);
*/
      } else {
         
         logger.info("비번찾기 else"+dto);
         String dtoPw = "입력정보를 확인해 주세요";
         HttpSession session = request.getSession();
         entity = new ResponseEntity<UserInfoDTO>(dto, HttpStatus.BAD_REQUEST);
/*         session.setAttribute("dtoPw", dtoPw);*/
      }

      return entity;
   }


   

   /* 회원가입 */

   /**
    * Sets the user info.
    *
    * @param dto the dto
    * @return the response entity
    * @throws Exception the exception
    */
   @RequestMapping(value = "/board/signup", method = RequestMethod.POST)
   public ResponseEntity<UserInfoDTO> setUserInfo(@RequestBody UserInfoDTO dto) throws Exception {
      ResponseEntity<UserInfoDTO> entity = null;
      try{
         userInfoService.signup(dto);
         entity = new ResponseEntity<UserInfoDTO>(dto,HttpStatus.OK);
      }catch(Exception e){
         e.printStackTrace();
         entity = new ResponseEntity<UserInfoDTO>(dto, HttpStatus.BAD_REQUEST);
      }
      return entity;
      
   }
   
   
   /**
    *   이메일 중복 체크 .
    *
    * @param dto the dto
    * @return the map
    * @throws Exception the exception
    */
   @RequestMapping(value="/board/signup/chkusr_id")
   @ResponseBody
   public Map<String, String> chkusr_id(@RequestBody UserInfoDTO dto) throws Exception {
      Map<String, String> resultMap = new HashMap<String, String>();
         int resultCnt = userInfoService.chkusr_id(dto);
         String result = "";
           String resultMsg = "";
           if ( resultCnt == 0 ){
               result = "success";
               resultMsg = "사용가능한 이메일입니다.";
           } else {
               result = "failure";
               resultMsg = "이미 사용중인 이메일입니다.";
              }
           resultMap.put("result", result);
            resultMap.put("resultMsg", resultMsg);
  
  return resultMap;
   }

      /**
       *   닉네임 중복 체크.
       *
       * @param dto the dto
       * @return the map
       * @throws Exception the exception
       */
   @RequestMapping(value="/board/signup/chkusr_name" ,method= RequestMethod.POST)
   @ResponseBody
   public Map<String, String> chkusr_name(@RequestBody UserInfoDTO dto) throws Exception {
      Map<String, String> resultMap = new HashMap<String, String>();
         int resultCnt = userInfoService.chkusr_name(dto);
         String result = "";
           String resultMsg = "";
           if ( resultCnt == 0 ){
               result = "success";
               resultMsg = "사용가능한 닉네임입니다.";
           } else {
               result = "failure";
               resultMsg = "이미 사용중인 닉네임입니다.";
              }
           resultMap.put("result", result);
            resultMap.put("resultMsg", resultMsg);
  
  return resultMap;
   }
}