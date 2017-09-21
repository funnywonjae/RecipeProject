package com.dj.service;

import java.util.List;

import com.dj.domain.QNADTO;

public interface QNAService {

	public void registQNA(QNADTO qnaDTO) throws Exception;
	public List<QNADTO> getQNA(QNADTO qnaDTO) throws Exception;
	public void delQNA(QNADTO qnaDTO) throws Exception;
	public void modQNA(QNADTO qnaDTO) throws Exception;
}
