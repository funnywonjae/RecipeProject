package com.dj.persistence;

import com.dj.domain.RefrigeratorDTO; 
import com.dj.domain.UserInfoDTO;

public interface RefrigeratorDAO {
	//����� ���ؼ� ���� �ҷ����� -�ڿ���0828
	public RefrigeratorDTO Refrigeratorload(String id) throws Exception;
	public void regist(RefrigeratorDTO dto) throws Exception;
	public void update(RefrigeratorDTO dto) throws Exception;
	public void delete(String id) throws Exception;
}
