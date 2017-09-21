package com.dj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.RefrigeratorDTO;
import com.dj.domain.UserInfoDTO;
@Repository

public class RefrigeratorDAOImpl implements RefrigeratorDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.refrigeratorMapper";
	//���̵� ���ؼ� ����� ���� �ҷ����� -�ڿ���-0828
	@Override
	public RefrigeratorDTO Refrigeratorload(String id) throws Exception {
		return session.selectOne(namespace+".refrigeratorLoad",id);
	}

	@Override
	public void regist(RefrigeratorDTO rdto) throws Exception {
			session.selectOne(namespace+".refrigeratorRegist",rdto);
	}

	@Override
	public void update(RefrigeratorDTO dto) throws Exception {
			session.selectOne(namespace+".refrigeratorDatachange",dto);
	}

	@Override
	public void delete(String id) throws Exception {
			session.selectOne(namespace+".refrigeratorDataDelete",id);
	}

}
