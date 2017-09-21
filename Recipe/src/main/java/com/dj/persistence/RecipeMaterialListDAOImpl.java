package com.dj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.Recipe_Material_ListDTO;
@Repository
public class RecipeMaterialListDAOImpl implements RecipeMaterialListDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.recipeMaterialListMapper";
	
	
	
	@Override
	public Recipe_Material_ListDTO RecipeMaterialListload(int no) throws Exception {
		return session.selectOne(namespace+".recipeMaterialListLoad",no);
	}

	@Override
	public void regist(Recipe_Material_ListDTO dto) throws Exception {
		
		
		session.selectOne(namespace+".recipeMaterialListRegist",dto);
	}

	@Override
	public void update(Recipe_Material_ListDTO dto) throws Exception {
		try {
		session.update(namespace+".recipeMaterialListDatachange",dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Recipe_Material_ListDTO dto) throws Exception {
		session.selectOne(namespace+".recipeMaterialListDataDelete",dto);
	}

}
