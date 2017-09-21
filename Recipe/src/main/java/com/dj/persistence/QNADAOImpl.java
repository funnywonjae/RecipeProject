package com.dj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.QNADTO;

@Repository
public class QNADAOImpl implements QNADAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.QNAMapper";
	
	@Override
	public List<QNADTO> QNAload(QNADTO dto) throws Exception {
		
		return session.selectList(namespace+".QNALoad",dto);
	}
	@Override
	public void regist(QNADTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".QNARegist",dto);
	}

	@Override
	public void update(QNADTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".QNADatachange",dto);
	}

	@Override
	public void delete(QNADTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".QNADataDelete",dto);
	}

}
