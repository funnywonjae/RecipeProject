package com.dj.service;

import java.util.List; 

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dj.domain.RefrigeratorDTO;
import com.dj.domain.UserInfoDTO;
import com.dj.persistence.Index_TableDAO;
import com.dj.persistence.RefrigeratorDAO;
import com.dj.persistence.UserInfoDAO;
import com.dj.persistence.MaterialListDAO;
import com.dj.domain.Material_ListDTO;

@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {

	@Inject 
	UserInfoDAO dao;
	@Inject
	RefrigeratorDAO daor;
	@Inject
	Index_TableDAO IndexDao;
	@Inject
	MaterialListDAO daom;
	
	@Override
	public RefrigeratorDTO readRefWithId(String id) throws Exception {
		// TODO Auto-generated method stub
		return daor.Refrigeratorload(id);
	}
	//�ڿ��� 0829
	@Override
	public synchronized void createRef(RefrigeratorDTO dto) throws Exception {
		
		String a=dto.getCategory();
		String b=IndexDao.readTableIndex(a);
		dto.setRef_index(b);
	
		daor.regist(dto);
		IndexDao.indexInc(dto.getCategory());
	}
	@Override
	public void updateRef(RefrigeratorDTO dto) throws Exception {

		daor.update(dto);
	}
	@Override
	public void deleteRef(String id) throws Exception {

		daor.delete(id);

	}
	@Override
	public List<Material_ListDTO> readML_List() throws Exception{
		return daom.MaterialListload();
	}
}
