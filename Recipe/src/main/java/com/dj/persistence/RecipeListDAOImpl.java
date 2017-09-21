package com.dj.persistence;

import java.util.List; 

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.Recipe_ListDTO;
import com.dj.domain.RecipeDTO;
@Repository
public class RecipeListDAOImpl implements RecipeListDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.recipeListMapper";

	@Override
	public RecipeDTO BookmarkRecipeListload(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".BookmarkRecipeListload",keyword);
	}
	
}
