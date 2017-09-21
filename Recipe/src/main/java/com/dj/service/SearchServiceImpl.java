package com.dj.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.dj.domain.CategoryDTO;
import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_ListDTO;
import com.dj.persistence.CategoryDAO;
import com.dj.persistence.RecipeListDAO;
@Repository
public class SearchServiceImpl implements SearchService {

	@Inject
	RecipeListDAO dao;
	
	@Inject
	CategoryDAO categoryDAO;
	
	@Override
	public List<CategoryDTO> getCategories() throws Exception {
		
		return categoryDAO.Categoryload();
	}
	


	@Override
	public List<RecipeDTO> getBookmarkRecipe(String[] bookmark) throws Exception {
		List<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		
		
		
		for(String a : bookmark){
			
			
			list.add(dao.BookmarkRecipeListload(a));
		}
		
		return list;
	}	

}
