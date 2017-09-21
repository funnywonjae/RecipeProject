package com.dj.persistence;

import java.util.List;

import com.dj.domain.FileListDTO;

public interface FileListDAO {
	public FileListDTO Fileload() throws Exception;
	public String FileListload(String no) throws Exception;
	public void regist(FileListDTO dto) throws Exception;
	public void update(FileListDTO dto) throws Exception;
	public void delete(FileListDTO dto) throws Exception;
	public String serverName(String category) throws Exception;
	public String modServerName(String index) throws Exception;
}
