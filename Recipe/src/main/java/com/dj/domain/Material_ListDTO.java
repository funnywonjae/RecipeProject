package com.dj.domain;

public class Material_ListDTO {
	
public Material_ListDTO() {
		
	}
	private int ml_no;
	private String ml_name;
	private String ml_unit;
	private String ml_index;
	private String cg_name;
	private String cg_id;
	
	private String category="material_list";
	
	public String getCategory() {
		return category;
	}
	
	public int getMl_no() {
		return ml_no;
	}
	public void setMl_no(int ml_no) {
		this.ml_no = ml_no;
	}
	public String getMl_name() {
		return ml_name;
	}
	public void setMl_name(String ml_name) {
		this.ml_name = ml_name;
	}
	public String getMl_unit() {
		return ml_unit;
	}
	public void setMl_unit(String ml_unit) {
		this.ml_unit = ml_unit;
	}
	public String getMl_index() {
		return ml_index;
	}
	public void setMl_index(String ml_index) {
		this.ml_index = ml_index;
	}
	public String getcg_name() {
		return cg_name;
	}
	public void setcg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	public String getCg_id() {
		return cg_id;
	}
	public void setCg_id(String cg_id) {
		this.cg_id = cg_id;
	}
	@Override
	public String toString() {
		return "Material_List [ml_no=" + ml_no + ", ml_name=" + ml_name + ", ml_unit=" + ml_unit + ", ml_index="
				+ ml_index + ", cg_name=" + cg_name + ", cg_id=" + cg_id + "]";
	}
	public Material_ListDTO(int ml_no, String ml_name, String ml_unit, String ml_index, String cg_name, String cg_id) {
		super();
		this.ml_no = ml_no;
		this.ml_name = ml_name;
		this.ml_unit = ml_unit;
		this.ml_index = ml_index;
		this.cg_name = cg_name;
		this.cg_id = cg_id;
	}
	
	

}
