package com.dj.persistence;

import java.util.List;

import com.dj.domain.QNADTO;

public interface QNADAO {
	public List<QNADTO> QNAload(QNADTO qna) throws Exception;
	public void regist(QNADTO dto) throws Exception;
	public void update(QNADTO dto) throws Exception;
	public void delete(QNADTO dto) throws Exception;
}
