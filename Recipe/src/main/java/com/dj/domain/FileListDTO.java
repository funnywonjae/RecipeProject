package com.dj.domain;

public class FileListDTO {
public FileListDTO() {
		
	}
	private String file_name;
	private String file_server;
	private String file_index;
	private String file_path;
	private String file_type;
	private String recipe_no;
	private String category="filelist";
	

	public String getCategory() {
		return category;
	}
	
	
	
	public String getRecipe_no() {
		return recipe_no;
	}



	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}



	public String getFile_name() {
		return file_name;
	}



	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}



	public String getFile_server() {
		return file_server;
	}
	
	
	
	public void setFile_server(String file_server) {
		this.file_server = file_server;
	}
	public String getFile_index() {
		return file_index;
	}
	public void setFile_index(String file_index) {
		this.file_index = file_index;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	@Override
	public String toString() {
		return "FileList [file_server=" + file_server + ", file_index=" + file_index + ", file_path=" + file_path
				+ ", file_type=" + file_type + "]";
	}
	public FileListDTO(String file_name,String file_server, String file_index,
			String file_path, String file_type,String recipe_no) {
		super();
		this.recipe_no = recipe_no;
		this.file_name = file_name;
		this.file_server = file_server;
		this.file_index = file_index;
		this.file_path = file_path;
		this.file_type = file_type;
	}
	

}
