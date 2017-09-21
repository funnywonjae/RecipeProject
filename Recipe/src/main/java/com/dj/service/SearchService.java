package com.dj.service;

import java.util.List;

import com.dj.domain.CategoryDTO;
import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_ListDTO;

public interface SearchService {

	
	public List<RecipeDTO> getBookmarkRecipe(String[] bookmark) throws Exception;
	
	public List<CategoryDTO> getCategories() throws Exception; 
}
