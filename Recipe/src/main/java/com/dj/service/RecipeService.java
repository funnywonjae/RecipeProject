package com.dj.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_Material_ListDTO;
import com.dj.domain.ThumbnailDTO;

public interface RecipeService {
	public Recipe_Material_ListDTO readMaterial (int no) throws Exception;
	public RecipeDTO readRecipe(int i) throws Exception;
	public Map<String, Object> readLimitRecipe(Map<String, Object> readData) throws Exception;
	public void setFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload,String category)throws Exception;
	public int setRecipe(RecipeDTO dto,String name) throws Exception;
	public List<RecipeDTO> readRandRecipe(String category) throws Exception;
	public ThumbnailDTO readThumbnail(String no) throws Exception;
	public boolean modRecipe(RecipeDTO dto)throws Exception; 
	public void modFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam MultipartFile upload,String category,String bno)throws Exception;
	public int delRecipe(String bno)throws Exception;
	public void setMaterial(Recipe_Material_ListDTO rmlDTO) throws Exception;
	public void modMaterial(Recipe_Material_ListDTO rmlDTO) throws Exception;
}
