package com.dj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.Index_TableDTO;
import com.dj.domain.UserInfoDTO;

@Repository
public class Index_TableDAOImpl implements Index_TableDAO{

	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.Index_TableMapper";
	
	@Override
	public  void indexInc(String category) throws Exception {
		session.selectOne(namespace+".updateTableIndex",category);
	}
	@Override
	public String readTableIndex(String category) throws Exception {
		return session.selectOne(namespace+".selectTableIndex",category);
	}
}
