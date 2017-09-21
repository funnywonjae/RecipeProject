package com.dj.controller;

import java.util.ArrayList;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dj.domain.CommentDTO;
import com.dj.domain.Material_ListDTO;
import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_Material_ListDTO;
import com.dj.domain.ThumbnailDTO;
import com.dj.domain.UserInfoDTO;
import com.dj.service.CommentService;
import com.dj.service.RecipeService;

@Controller
public class BoardController {
	@Inject
	RecipeService recipeService;
	@Inject
	CommentService commentService;
	// 글번호로 레시피 불러오기

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	
	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	public String ReadRecipe(HttpServletRequest request, Model model) throws Exception {
				model.addAttribute("item", recipeService.readRecipe(Integer.parseInt(request.getParameter("no").toString())));
				return "/board/readContent";
	} 



	
	@RequestMapping(value = "/board/material_search")
	public @ResponseBody List<Material_ListDTO> material_search(
			@RequestParam(required = true, value = ("word")) String word, HttpSession session) {
		ResponseEntity<String> entity = null;
		try {
			List<Material_ListDTO> material_list = (List<Material_ListDTO>) (session.getAttribute("ml_List"));
			List<Material_ListDTO> result = new ArrayList<Material_ListDTO>();
			for (Material_ListDTO list : material_list) {
				if (list.getMl_name().indexOf(word) > -1) {
					result.add(list);
				}
			}
			return result;
		} catch (Exception e) {
				e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/board/modFileUpload")
	public void modFileUp(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			MultipartHttpServletRequest upload) {
		Iterator files = upload.getFileNames();
		MultipartFile multi = upload.getFile((String) files.next());
		try {
			recipeService.modFile(request, response, multi, "thumbnail", session.getAttribute("bno").toString());
			session.removeAttribute("bno");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/board/delContent", method = RequestMethod.POST)
	public String delContent(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		RecipeDTO item = recipeService.readRecipe(Integer.parseInt(request.getParameter("bno").toString()));

		if (item.getusr_id().equals(((UserInfoDTO) session.getAttribute("userInfo")).getUsr_id())) {
			System.out.println("여깁니까");
			model.addAttribute("pageUrl", "main");
			recipeService.delRecipe(request.getParameter("bno").toString());
		}
		return "forward:/";

	}

	@RequestMapping(value = "/board/modContent", method = RequestMethod.POST)
	public String modContent(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		RecipeDTO item = recipeService.readRecipe(Integer.parseInt(request.getParameter("bno").toString()));
		if (item.getusr_id().equals(((UserInfoDTO) session.getAttribute("userInfo")).getUsr_id())) {
			model.addAttribute("item", item);
			model.addAttribute("pageUrl", "modContent");
			return "/static/index";
		} else {
			return "/";
		}

	}

	@RequestMapping(value = "/board/modWrite", method = RequestMethod.POST)
	public ResponseEntity<String> postUploadModPage(HttpServletRequest request, HttpSession session,
			@RequestBody RecipeDTO dto) throws Exception {

		ResponseEntity<String> entity = null;

		if (recipeService.modRecipe(dto)) {

			entity = new ResponseEntity<String>("글쓰기 성공", HttpStatus.OK);
			session.setAttribute("bno", dto.getRecipe_no());
		} else {

			entity = new ResponseEntity<String>("글쓰기 실패", HttpStatus.BAD_REQUEST);
		}

		return entity;

	}

	@RequestMapping(value = "/board/writeMaterial", method = RequestMethod.POST)
	public ResponseEntity<String> materialUploadPage(HttpServletRequest request, HttpSession session,
			@RequestParam(required = true, value = "ml_no") String no,
			@RequestParam(required = true, value = "ml_cnt") String cnt) throws Exception {
		Recipe_Material_ListDTO rmlDTO = new Recipe_Material_ListDTO();
		rmlDTO.setMl_no(no);
		rmlDTO.setMl_cnt(cnt);
		ResponseEntity<String> entity = null;
		try {
			recipeService.setMaterial(rmlDTO);
			entity = new ResponseEntity<String>("재료등록 성공", HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<String>("재료등록 실패", HttpStatus.BAD_REQUEST);

		}
		return entity;

	}

	@RequestMapping(value = "/board/modMaterial", method = RequestMethod.POST)
	public ResponseEntity<String> materialModPage(HttpServletRequest request, HttpSession session,
			@RequestParam(required = true, value = "ml_no") String no,
			@RequestParam(required = true, value = "ml_cnt") String cnt) throws Exception {
		Recipe_Material_ListDTO rmlDTO = new Recipe_Material_ListDTO();

		rmlDTO.setMl_no(no);
		rmlDTO.setMl_cnt(cnt);
		rmlDTO.setRecipe_no(session.getAttribute("bno").toString());
		System.out.println(rmlDTO);
		ResponseEntity<String> entity = null;
		try {
			recipeService.modMaterial(rmlDTO);
			entity = new ResponseEntity<String>("재료수정 성공", HttpStatus.OK);

		} catch (Exception e) {
			entity = new ResponseEntity<String>("재료수정 실패", HttpStatus.BAD_REQUEST);

		}
		return entity;

	}

	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public ResponseEntity<String> postUploadPage(HttpServletRequest request, HttpSession session,
			@RequestBody RecipeDTO recipeDTO) throws Exception {

		ResponseEntity<String> entity = null;

		int result = recipeService.setRecipe(recipeDTO,((UserInfoDTO) session.getAttribute("userInfo")).getUsr_id());
		if (result == 1) {
			entity = new ResponseEntity<String>("글쓰기 성공", HttpStatus.OK);
		} else {

			entity = new ResponseEntity<String>("글쓰기 실패", HttpStatus.BAD_REQUEST);

		}
		return entity;

	}

	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String getUploadPage(HttpServletRequest request) throws Exception {
		return "/board/write";
	}

	@ResponseBody
	@RequestMapping(value = "/community/imageUpload", method = RequestMethod.POST)
	public void communityImageUpload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload) throws Exception {

		recipeService.setFile(request, response, upload, "filelist");

		return;
	}

	@RequestMapping(value = "/board/fileUpload")
	public void fileUp(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest upload,
			HttpSession session) {

		Iterator files = upload.getFileNames();
		MultipartFile multi = upload.getFile((String) files.next());
		try {
			recipeService.setFile(request, response, multi, "thumbnail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 댓글 입력 컨트롤러
	@RequestMapping(value = "/board/comment")
	public ResponseEntity<CommentDTO> setcommentInfo(@RequestBody CommentDTO dto) throws Exception {
		ResponseEntity<CommentDTO> entity = null;
		try {
			commentService.registComment(dto);
			entity = new ResponseEntity<CommentDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<CommentDTO>(dto, HttpStatus.BAD_REQUEST);
		}

		return entity;

	}

	// 댓글 삭제 컨트롤러
	@RequestMapping(value = "/board/delcomment")
	public ResponseEntity<String> delcomment(HttpServletRequest request, @RequestBody CommentDTO dto) throws Exception {
		ResponseEntity<String> entity = null;
		try {
			commentService.delComment(dto.getCom_index());
			entity = new ResponseEntity<String>("성공", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>("실패", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;

	}

	// 댓글 수정 컨트롤러
	@RequestMapping(value = "/board/modcomment")
	public ResponseEntity<String> modcomment(HttpServletRequest request, @RequestBody CommentDTO dto) throws Exception {
		ResponseEntity<String> entity = null;
		try {
			commentService.modifyComment(dto);
			entity = new ResponseEntity<String>("성공", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>("실패", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}

		return entity;

	}
	
}
