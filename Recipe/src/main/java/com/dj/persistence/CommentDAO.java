package com.dj.persistence;

import java.util.List;

import com.dj.domain.CommentDTO;

public interface CommentDAO {
	public List<CommentDTO> Commentload(int i)  throws Exception;
	//댓글 등록 DAO
	public void regist(CommentDTO dto) throws Exception;
	//댓글 수정 DAO
	public void update(CommentDTO dto) throws Exception;
	//댓글 삭제 DAO
	public void delete(String index) throws Exception;
}

