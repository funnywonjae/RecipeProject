package com.dj.persistence;

import java.util.List;
import java.util.Map;

import com.dj.domain.RecipeDTO;

public interface RecipeDAO {
	public List<RecipeDTO> getSearchLimitRecipe(Map<String, Object> readData) throws Exception;
	public List<RecipeDTO> getMainLimitRecipe(Map<String, Object> readData) throws Exception;
	public String getIndex (String bno) throws Exception;
	public List<RecipeDTO> BookmarkRecipeload(List<RecipeDTO> bookmark) throws Exception;
	public RecipeDTO Recipeload(int i) throws Exception;
   public int regist(RecipeDTO dto) throws Exception;

   public int update(RecipeDTO dto) throws Exception;
	public int delete(String bno) throws Exception;
	public List<RecipeDTO> getRandomRecipe(String category) throws Exception;
	
}
