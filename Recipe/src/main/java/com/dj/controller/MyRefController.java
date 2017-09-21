package com.dj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dj.domain.RefrigeratorDTO;
import com.dj.domain.UserInfoDTO;
import com.dj.service.RefrigeratorService;

@Controller
public class MyRefController {

   private static final Logger logger = LoggerFactory.getLogger(MyRefController.class);

   @Inject
   private RefrigeratorService service;
    
   
   //나의냉장고 등록
   @RequestMapping(value = "/board/ref_regist", method = RequestMethod.POST)
   public ResponseEntity<String> myRefRegistWithRefDTO(HttpServletRequest request,
         @RequestParam(value = "ml_no", required = true) String mlno,
         @RequestParam(value = "ml_edate", required = true) String edate,
         @RequestParam(value = "ml_cnt", required = true) String count,
         Model model
         )
         throws Exception {
      HttpSession session = request.getSession();
      RefrigeratorDTO rdto = new RefrigeratorDTO(((UserInfoDTO) session.getAttribute("userInfo")).getUsr_id(), mlno, edate, count);
     
     

      ResponseEntity<String> entity=null;
		
		try {
			 service.createRef(rdto);
			entity=new ResponseEntity<String>("등록성공",HttpStatus.OK);
			
		} catch (Exception e) {
			entity=new ResponseEntity<String>("등록실패",HttpStatus.BAD_REQUEST);

		}
        List<String> my_ml_no = new ArrayList<String>();
		List<String> my_ml_cnt = new ArrayList<String>();
		List<String> my_ml_edate = new ArrayList<String>();
		
		
		for(String a : rdto.getRef_Ml_no().split(",")){
			my_ml_no.add(a);
		}
		for(String a : rdto.getRef_cnt().split(",")){
			my_ml_cnt.add(a);
		}
		for(String a : rdto.getRef_edate().split(",")){
			my_ml_edate.add(a);
		}
		session.setAttribute("my_ml_no",my_ml_no);
		session.setAttribute("my_ml_cnt",my_ml_cnt);
		session.setAttribute("my_ml_edate",my_ml_edate);
		
    return entity;

   }
//사용중 나의 냉장고 불러오기
   @RequestMapping(value = "/board/ref_update", method = RequestMethod.POST)
   public ResponseEntity<String> myRefUpdateWithRefDTO(HttpServletRequest request,
         @RequestParam(value = "ml_no", required = true) String mlno,
         @RequestParam(value = "ml_edate", required = true) String edate,
         @RequestParam(value = "ml_cnt", required = true) String count,
         Model model
         )
         throws Exception {
      HttpSession session = request.getSession();
      RefrigeratorDTO dto = new RefrigeratorDTO(((UserInfoDTO) session.getAttribute("userInfo")).getUsr_id(), mlno, edate, count);
   
     
     
      ResponseEntity<String> entity=null;
		
		try {
			 service.updateRef(dto);
			entity=new ResponseEntity<String>("변경성공",HttpStatus.OK);
			
		} catch (Exception e) {
			entity=new ResponseEntity<String>("변경실패",HttpStatus.BAD_REQUEST);

		}
		    List<String> my_ml_no = new ArrayList<String>();
			List<String> my_ml_cnt = new ArrayList<String>();
			List<String> my_ml_edate = new ArrayList<String>();
			
			for(String a : dto.getRef_Ml_no().split(",")){
				my_ml_no.add(a);
			}
			for(String a : dto.getRef_cnt().split(",")){
				my_ml_cnt.add(a);
			}
			for(String a : dto.getRef_edate().split(",")){
				my_ml_edate.add(a);
			}
			
			session.setAttribute("my_ml_no",my_ml_no);
			session.setAttribute("my_ml_cnt",my_ml_cnt);
			session.setAttribute("my_ml_edate",my_ml_edate);
			
      return entity;


   }
//사용중 냉장고 초기화
   @RequestMapping(value = "/board/ref_delete", method = RequestMethod.POST)
   public String myRefDelete(Model model,HttpServletRequest request) throws Exception {
      HttpSession session = request.getSession();
      service.deleteRef(((UserInfoDTO) session.getAttribute("userInfo")).getUsr_id());
      session.removeAttribute("my_ml_no");
      session.removeAttribute("my_ml_cnt");
      session.removeAttribute("my_ml_edate");
      model.addAttribute("pageUrl","myRef");
      return "/static/index";

   }
 
   
}