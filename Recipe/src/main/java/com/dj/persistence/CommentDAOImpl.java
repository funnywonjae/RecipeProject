package com.dj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.CommentDTO;


@Repository
public class CommentDAOImpl implements CommentDAO {
	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.commentMapper";
	
	@Override
	public List<CommentDTO> Commentload(int i) throws Exception {
		return session.selectList(namespace+".CommentLoad",i);
	}
	//댓글 등록 DAO
	@Override
	public void regist(CommentDTO dto) throws Exception {
		session.insert(namespace+".CommentRegist",dto);
	}
	//댓글 수정 DAO
	@Override
	public void update(CommentDTO dto) throws Exception {
		session.selectOne(namespace+".CommentDatachange",dto);
	}
	//댓글 삭제 DAO
	@Override
	public void delete(String index) throws Exception {
		session.selectOne(namespace+".CommentDataDelete",index);
	}
	

}

