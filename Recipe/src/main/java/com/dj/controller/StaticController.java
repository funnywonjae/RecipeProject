package com.dj.controller;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dj.domain.Material_ListDTO;
import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_Material_ListDTO;
import com.dj.domain.RefrigeratorDTO;
import com.dj.domain.ThumbnailDTO;
import com.dj.domain.UserInfoDTO;
import com.dj.service.CommentService;
import com.dj.service.RecipeService;
import com.dj.service.RefrigeratorService;
import com.dj.service.SearchService;
import com.dj.service.UserInfoService;

@Controller
public class StaticController {
	@Inject
	SearchService searchService;
	@Inject
	RecipeService recipeService;
	@Inject
	RefrigeratorService refService;
	@Inject
	CommentService commentService;
	@Inject
	UserInfoService userInfoService;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	
	@RequestMapping("/board/allList")
	public @ResponseBody Map<String, Object> allList_moreList(
			@RequestParam(value="cnt",required=true)String cnt,
			@RequestParam(value="category",required=true)String category,
			@RequestParam(value="action",required=true)String action) throws Exception {
		try {
			
			Map<String,Object> readData = new HashMap<String,Object>();
			logger.info(readData.toString());
			readData.put("cnt",cnt);
			readData.put("action",action);

			readData.put("category", changeString(category));
			
			
			return recipeService.readLimitRecipe(readData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/")
	public String postUploadPage(HttpSession session, HttpServletRequest request, Model model) throws Exception {

		List<Material_ListDTO> Ml_List = new ArrayList<Material_ListDTO>();
		Ml_List = refService.readML_List();
		session.setAttribute("ml_List", Ml_List);
		session.setAttribute("categories",searchService.getCategories());

		session.removeAttribute("ml_no");
		session.removeAttribute("ml_cnt");
		session.removeAttribute("mlDTO");

		if ((request.getParameter("page") == null) || (request.getParameter("page").equals("main"))) {
			
			logger.info("if臾�");
			String category = (String)request.getParameter("category");
			List<RecipeDTO> randList = recipeService.readRandRecipe(category);
			List<ThumbnailDTO> randFile = setThumbnailDTO(randList);
			model.addAttribute("randList", randList);
			model.addAttribute("randListThumbnail", randFile);
			model.addAttribute("category",category);
			model.addAttribute("pageUrl", "main");
		}
			
		else if (request.getParameter("page").equals("readContent")) {
					
			int bno = Integer.parseInt(request.getParameter("no").toString());
			model.addAttribute("item", recipeService.readRecipe(bno));
			
			model.addAttribute("comment", commentService.readComment(Integer.parseInt(request.getParameter("no").toString())));
			  logger.info(commentService.readComment(Integer.parseInt(request.getParameter("no").toString())).toString());			
			Recipe_Material_ListDTO dto = recipeService.readMaterial(bno);
			List<String> ml_no = new ArrayList<String>();
			List<String> ml_cnt = new ArrayList<String>();

			for(String a : dto.getMl_no().split(",")){	
				ml_no.add(a);
			}
			
			for(String a : dto.getMl_cnt().split(",")){
				ml_cnt.add(a);
			}
			
			List<String> compare = new ArrayList<String>();
			List<String> my_ml_no = (List<String>)session.getAttribute("my_ml_no");
			List<String> my_ml_cnt = (List<String>)session.getAttribute("my_ml_cnt");
			
			for (int i = 0; i < ml_no.size(); i++) {
				if (my_ml_no == null) {
					compare.add("냉장고를 설정해주세요.");
				}else{
				for (int b = 0; b < my_ml_no.size(); b++) {
				
						if (ml_no.get(i).equals(my_ml_no.get(b))) {
							compare.add(my_ml_cnt.get(b));
						} else {
							compare.add("0");
						}
				
				}
				}
			}

			model.addAttribute("compare", compare);
			session.setAttribute("ml_no", ml_no);
			session.setAttribute("ml_cnt", ml_cnt);
			

			List<Material_ListDTO> mlDTO = new ArrayList<Material_ListDTO>();
			
			for(String a : ml_no){
				for(Material_ListDTO b : (List<Material_ListDTO>) session.getAttribute("ml_List")){
					if(a.equals(""+b.getMl_no())){
						mlDTO.add(b);
						break;
					}
				}
			}
			session.setAttribute("mlDTO",mlDTO);
			Map<String,Object> bookmarks = null;
			if((UserInfoDTO)session.getAttribute("userInfo")!=null){
			bookmarks = userInfoService.getBookmarkList((UserInfoDTO)session.getAttribute("userInfo"));
			
			model.addAttribute("list",bookmarks.get("bookmark"));
			}
			model.addAttribute("pageUrl", "readContent");
		}
	
		else if(request.getParameter("page").equals("myRef"))  
		{	
		
			List<String> my_ml_no = (List<String>)session.getAttribute("my_ml_no");
			List<String> my_ml_no2= new ArrayList<String>();
			if(my_ml_no==null||my_ml_no.equals(my_ml_no2)) {
				model.addAttribute("pageUrl","myRef");
				return "/static/index";
			}
			List<Material_ListDTO> mlDTO = new ArrayList<Material_ListDTO>();
			
			for(String a : my_ml_no){
				for(Material_ListDTO b : (List<Material_ListDTO>) session.getAttribute("ml_List")){
					if(a.equals(""+b.getMl_no())){
						mlDTO.add(b);
						break;
					}
				}
			}
			logger.info(""+mlDTO);			
			session.setAttribute("mlDTO",mlDTO);
			model.addAttribute("pageUrl", "modmyRef");
			
			
		}
		else if(request.getParameter("page").equals("bookmark_page")){
			if((UserInfoDTO)session.getAttribute("userInfo")!=null){
			Map<String,Object> bookmarks = userInfoService.getBookmarkList((UserInfoDTO)session.getAttribute("userInfo"));

			List<RecipeDTO> list =(List<RecipeDTO>) bookmarks.get("bookmarklist");
			List<ThumbnailDTO> thumbFile = setThumbnailDTO(list);
			model.addAttribute("list",list);
			model.addAttribute("thumbFile", thumbFile);
			System.out.println(thumbFile);
			model.addAttribute("pageUrl", "bookmark");
		}
		}
		else {
						
			model.addAttribute("pageUrl", request.getParameter("page"));
		}

		return "/static/index";
	}
	
	private String changeString(String str){
		if(str.equals("")){
			str = null;
		}
		return str;
	}

	public List<ThumbnailDTO> setThumbnailDTO(List<RecipeDTO> list) throws Exception{
		List<ThumbnailDTO> randFile = new ArrayList<ThumbnailDTO>();

		for(RecipeDTO a : list){
			randFile.add(recipeService.readThumbnail(a.getRecipe_no()));
		}
		return randFile;
	}
	
}
