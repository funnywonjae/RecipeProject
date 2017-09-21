package com.dj.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.RecipeDTO;

@Repository
public class RecipeDAOImpl implements RecipeDAO {

	
	
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.recipeMapper";

	@Override
	public String getIndex(String bno) throws Exception {
		
		return session.selectOne(namespace+".getIndex",bno);
	}
	
	@Override
	public List<RecipeDTO> getSearchLimitRecipe(Map<String, Object> readData) throws Exception {
		System.out.println(session.selectList(namespace+".searchResultListLoad",readData));
		return session.selectList(namespace+".searchResultListLoad",readData);
	}
	@Override
	public List<RecipeDTO> getMainLimitRecipe(Map<String, Object> readData) throws Exception {
		
			return session.selectList(namespace+".LimitRecipeData",readData);
		
	}
	
	@Override
	public List<RecipeDTO> getRandomRecipe(String category) throws Exception {
		System.out.println("qweqwe"+category);
		return session.selectList(namespace+".RandomRecipeData",category);
		
	}
	
	@Override
	public int delete(String bno) throws Exception {
		return session.delete(namespace + ".RecipeDataDelete", bno);

	}

	@Override
	public int update(RecipeDTO dto) throws Exception {
		return session.update(namespace + ".RecipeDatachange", dto);

	}

	/*@Override
	public String getNum() throws Exception {
		return session.selectOne(namespace + ".RecipeNum");
	}
*/
	@Override
	public int regist(RecipeDTO dto) throws Exception {
		return session.insert(namespace+".RecipeRegist",dto);		
	}

	@Override
	public RecipeDTO Recipeload(int i) throws Exception {
			
		return session.selectOne(namespace + ".RecipeLoad", i);
	}

	@Override
	public List<RecipeDTO> BookmarkRecipeload(List<RecipeDTO> list) throws Exception {

		return session.selectList(namespace + ".BookmarkRecipeLoad", list);
	}

}
