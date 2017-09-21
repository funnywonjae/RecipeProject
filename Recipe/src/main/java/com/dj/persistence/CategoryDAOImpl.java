package com.dj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.CategoryDTO;
@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.categoryMapper";
	
	@Override
	public List<CategoryDTO> Categoryload() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".CategoryLoad");
	}

	@Override
	public void regist(CategoryDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".CategoryRegist",dto);
	}

	@Override
	public void update(CategoryDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".CategoryDatachange",dto);
	}

	@Override
	public void delete(CategoryDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".CategoryDataDelete",dto);
	}

}
