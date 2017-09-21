package com.dj.service;

import java.util.List;

import com.dj.domain.RefrigeratorDTO; 
import com.dj.domain.UserInfoDTO;
import com.dj.domain.Material_ListDTO;

public interface RefrigeratorService {
	//���̵� ���� ������ �ҷ�����-�ڿ��� 0828
	public RefrigeratorDTO readRefWithId(String id) throws Exception;
	public void createRef(RefrigeratorDTO dto) throws Exception;
	public void updateRef(RefrigeratorDTO dto) throws Exception;
	public void deleteRef(String id)throws Exception;
	//��Ḯ��Ʈ ����Ʈ �������� �ҷ����� �ż��� �߰�-�ڿ��� 0828
	List<Material_ListDTO> readML_List() throws Exception;
}
