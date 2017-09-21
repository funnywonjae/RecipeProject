package com.dj.domain;

public class CategoryDTO {
	public CategoryDTO() {
		
	}
	private String cg_grp;
	private String cg_id;
	private String cg_grpid;
	private String cg_name;
	private String cg_index;
	
	private String category="category";
	
	public String getCategory() {
		return category;
	}
	
	
	
	public String getCg_grp() {
		return cg_grp;
	}
	
	
	
	
	public void setCg_grp(String cg_grp) {
		this.cg_grp = cg_grp;
	}
	public String getCg_id() {
		return cg_id;
	}
	public void setCg_id(String cg_id) {
		this.cg_id = cg_id;
	}
	public String getCg_grpid() {
		return cg_grpid;
	}
	public void setCg_grpid(String cg_grpid) {
		this.cg_grpid = cg_grpid;
	}
	public String getcg_name() {
		return cg_name;
	}
	public void setcg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	public String getCg_index() {
		return cg_index;
	}
	public void setCg_index(String cg_index) {
		this.cg_index = cg_index;
	}
	@Override
	public String toString() {
		return "Category [cg_grp=" + cg_grp + ", cg_id=" + cg_id + ", cg_grpid=" + cg_grpid
				+ ", cg_name=" + cg_name + ", cg_index=" + cg_index + "]";
	}
	public CategoryDTO(String cg_grp, String cg_id, String cg_grpid,String cg_name, String cg_index) {
		super();
		this.cg_grp = cg_grp;
		this.cg_id = cg_id;
		this.cg_grpid = cg_grpid;
		this.cg_name = cg_name;
		this.cg_index = cg_index;
	}
	
	

}
