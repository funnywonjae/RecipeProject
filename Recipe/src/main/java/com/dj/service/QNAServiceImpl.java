package com.dj.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dj.domain.QNADTO;
import com.dj.persistence.Index_TableDAO;
import com.dj.persistence.QNADAO;

@Service
public class QNAServiceImpl implements QNAService{
	@Inject QNADAO qnaDAO;
	@Inject Index_TableDAO index_tableDAO;
	@Override
	public void delQNA(QNADTO qnaDTO) throws Exception {
		
		qnaDAO.delete(qnaDTO);
		
	}
	@Override
	public void modQNA(QNADTO qnaDTO) throws Exception {

		qnaDAO.update(qnaDTO);
		
	}
	
	
	public synchronized void registQNA(QNADTO qnaDTO) throws Exception{
				try {
				qnaDTO.setQna_index(index_tableDAO.readTableIndex(qnaDTO.getCategory()));
				qnaDAO.regist(qnaDTO);
				index_tableDAO.indexInc(qnaDTO.getCategory());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	@Override
	public List<QNADTO> getQNA(QNADTO qnaDTO) {
		
		try {
			return qnaDAO.QNAload(qnaDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
