package com.dj.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.ThumbnailDTO;
@Repository
public class ThumbnailDAOImpl implements ThumbnailDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.thumbnailMapper";
	
	@Override
	public ThumbnailDTO getReadThumbnail(String no) throws Exception {
		return session.selectOne(namespace+".thumbnailLoad",no);
	}
	 
	@Override
	public void regist(ThumbnailDTO dto) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("d12d21 123123 12ed "+dto);
		
		session.selectOne(namespace+".thumbnailRegist",dto);
	}

	@Override
	public void update(ThumbnailDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".thumbnailDatachange",dto);
	}

	@Override
	public void delete(ThumbnailDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace+".thumbnailDataDelete",dto);
	}

}
