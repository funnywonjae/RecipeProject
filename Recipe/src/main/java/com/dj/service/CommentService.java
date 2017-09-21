package com.dj.service;

import java.util.List;

import com.dj.domain.CommentDTO;

public interface CommentService {
	//댓글 목록 읽는 서비스 
	public List<CommentDTO> readComment(int i)  throws Exception;
	//댓글 등록 서비스
	public CommentDTO registComment(CommentDTO dto) throws Exception;
	//댓글 삭제 서비스
	public void delComment(String index) throws Exception;
	//댓글 수정 서비스
	public CommentDTO modifyComment(CommentDTO dto) throws Exception;
}