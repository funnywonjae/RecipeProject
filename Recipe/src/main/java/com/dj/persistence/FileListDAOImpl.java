package com.dj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.FileListDTO;

@Repository
public class FileListDAOImpl implements FileListDAO {

	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.filelistMapper";

	@Override
	public String serverName(String category) throws Exception {
		return session.selectOne(namespace + ".serverName",category);

	}


	@Override
	public String modServerName(String index) throws Exception {
		return session.selectOne(namespace + ".modServerName",index);

	}
	
	@Override
	public String FileListload(String no) throws Exception {

		return session.selectOne(namespace + ".fileLoad_No", no);

	}

	@Override
	public FileListDTO Fileload() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".fileLoad_Index");

	}

	@Override
	public void regist(FileListDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace + ".FileListRegist", dto);

	}

	@Override
	public void update(FileListDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace + ".FileListDatachange", dto);

	}

	@Override
	public void delete(FileListDTO dto) throws Exception {
		// TODO Auto-generated method stub
		session.selectOne(namespace + ".FileListDataDelete", dto);

	}

}
