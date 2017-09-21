package com.dj.persistence;

import java.util.List;

import com.dj.domain.CategoryDTO;

public interface CategoryDAO {
	public List<CategoryDTO> Categoryload() throws Exception;
	public void regist(CategoryDTO dto) throws Exception;
	public void update(CategoryDTO dto) throws Exception;
	public void delete(CategoryDTO dto) throws Exception;
}
