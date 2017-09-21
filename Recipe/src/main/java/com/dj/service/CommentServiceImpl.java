package com.dj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dj.domain.CommentDTO;
import com.dj.persistence.CommentDAO;
import com.dj.persistence.Index_TableDAO;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Inject CommentDAO commentDAO;
	@Inject Index_TableDAO index_tableDAO;
	
	//댓글 삭제
	@Override
	public void delComment(String index) throws Exception {
		commentDAO.delete(index);
		
	}
	//댓글 등록
	@Override
	public synchronized CommentDTO registComment(CommentDTO dto) throws Exception {
		dto.setCom_index(index_tableDAO.readTableIndex(dto.getCategory()));
		commentDAO.regist(dto);
		index_tableDAO.indexInc(dto.getCategory());
		return dto;
		
	}
	//댓글 로드
	@Override
	public List<CommentDTO> readComment(int i) throws Exception {
		try {
			return commentDAO.Commentload(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//댓글 수정 
	@Override
	public synchronized CommentDTO modifyComment(CommentDTO dto) throws Exception {
		commentDAO.update(dto);
		return dto;
	}
	
}

