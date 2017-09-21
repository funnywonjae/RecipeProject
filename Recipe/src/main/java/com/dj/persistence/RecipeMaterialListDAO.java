package com.dj.persistence;

import com.dj.domain.Recipe_Material_ListDTO;

public interface RecipeMaterialListDAO {
	public Recipe_Material_ListDTO RecipeMaterialListload(int no) throws Exception;
	public void regist(Recipe_Material_ListDTO dto) throws Exception;
	public void update(Recipe_Material_ListDTO dto) throws Exception;
	public void delete(Recipe_Material_ListDTO dto) throws Exception;
}
