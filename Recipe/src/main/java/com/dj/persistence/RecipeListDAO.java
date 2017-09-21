package com.dj.persistence;

import java.util.List;

import com.dj.domain.Recipe_ListDTO;
import com.dj.domain.RecipeDTO;
//��ġ��ɿ����� �ٿ� ���� �ڿ���-0828
public interface RecipeListDAO {
	/*public List<RecipeDTO> RecipeListload(Recipe_ListDTO dto) throws Exception;*/
/*	public List<RecipeDTO> listinfoload(Recipe_ListDTO dto) throws Exception;*/
	public RecipeDTO BookmarkRecipeListload(String keyword) throws Exception;
}
