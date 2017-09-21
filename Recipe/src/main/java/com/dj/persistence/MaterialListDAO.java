package com.dj.persistence;

import java.util.List;

import com.dj.domain.Material_ListDTO;
import com.dj.domain.Recipe_ListDTO;

public interface MaterialListDAO {
	//����Ʈ �������� �޾ƿ���-�ڿ���-0828
	public List<Material_ListDTO> MaterialListload() throws Exception;
	public int regist(Recipe_ListDTO dto) throws Exception;
	public void update(Recipe_ListDTO dto) throws Exception;
	public void delete(Recipe_ListDTO dto) throws Exception;
}
