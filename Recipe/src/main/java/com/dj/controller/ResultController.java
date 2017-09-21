package com.dj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_ListDTO;
import com.dj.domain.UserInfoDTO;
import com.dj.service.RecipeService;
import com.dj.service.SearchService;
import com.dj.service.UserInfoService;

@Controller

public class ResultController {

	private static final Logger logger = 
			LoggerFactory.getLogger(ResultController.class);
	
	@Inject
	private UserInfoService userService;
	@Inject
	private RecipeService recipeService;
	//�꽌移섏꽌鍮꾩뒪 �씤�젥�듃 諛뺤쁺�깭-0828
	@Inject
	private SearchService searchService;
	@Inject
	private UserInfoService userInfoService;


	@RequestMapping("/board/result_allList")
	public @ResponseBody Map<String, Object> allList_moreList(
			@RequestParam(value="type",required=true)String type,
			@RequestParam(value="word",required=true)String word,
			@RequestParam(value="cnt",required=true)String cnt,
			@RequestParam(value="action",required=true)String action) throws Exception {
		try {
			
			Map<String,Object> readData = new HashMap<String,Object>();
			readData.put("type",type);
			readData.put("word",word);
			readData.put("cnt",cnt);
			readData.put("action",action);
			logger.info(readData.toString());

			
			return recipeService.readLimitRecipe(readData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	@RequestMapping(value = "/board/result", method = RequestMethod.POST)
	   public String getSearchPage(@RequestParam(value = "word", required = true) String word,
	         @RequestParam(value = "searchType", required = true) String searchType, Model model) throws Exception {
	    
	      Recipe_ListDTO dto = new Recipe_ListDTO();
	      dto.setKeyword(word);
	      if(searchType!="") {
	      dto.setSearchType(searchType);}
	      System.out.println("result = " +dto);
	      
	      model.addAttribute("pageUrl","result");
	      model.addAttribute("list",dto);
	      return "/static/index";
	   }
	
	@RequestMapping(value = "/board/bookmark_del", method = RequestMethod.POST)
	   public ResponseEntity<String> delBookmark(@RequestParam(value = "bno", required = true) String bno,
			   HttpSession session, Model model,HttpServletRequest request,
			   HttpServletResponse response) throws Exception {
		ResponseEntity<String> entity = null;
		
		
		try{
			userInfoService.delBookmarkList(bno,(UserInfoDTO)session.getAttribute("userInfo"));
			entity = new ResponseEntity<String>("",HttpStatus.OK);
			boolean result = userInfoService.loginUser((UserInfoDTO)session.getAttribute("userInfo"),session, request, response);	
		}
		catch (Exception e) {
			entity = new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
			
		
		return entity;
	   }
	
	@RequestMapping(value = "/board/bookmark_add", method = RequestMethod.POST)
	   public ResponseEntity<String> addBookmark(@RequestParam(value = "bno", required = true) String bno,
			   HttpSession session, Model model,HttpServletRequest request,
			   HttpServletResponse response) throws Exception {
		ResponseEntity<String> entity = null;

		try{
		userInfoService.addBookmarkList(bno,(UserInfoDTO)session.getAttribute("userInfo"));
		entity = new ResponseEntity<String>("",HttpStatus.OK);
		boolean result = userInfoService.loginUser((UserInfoDTO)session.getAttribute("userInfo"),session, request, response);	
	}
	catch (Exception e) {
		entity = new ResponseEntity<String>("",HttpStatus.BAD_REQUEST);
		e.printStackTrace();
	}
		
	
	return entity;
	   }

}
