package com.dj.persistence;

import java.util.List;

import com.dj.domain.ThumbnailDTO;

public interface ThumbnailDAO {
	public void regist(ThumbnailDTO dto) throws Exception;
	public void update(ThumbnailDTO dto) throws Exception;
	public void delete(ThumbnailDTO dto) throws Exception;	
	public ThumbnailDTO getReadThumbnail(String no) throws Exception;
}
