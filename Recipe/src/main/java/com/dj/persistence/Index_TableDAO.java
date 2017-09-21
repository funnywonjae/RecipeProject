package com.dj.persistence;


public interface Index_TableDAO {

	public void indexInc(String category) throws Exception;
	public String readTableIndex(String category) throws Exception;
}
