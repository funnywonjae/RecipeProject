package com.dj.domain;

public class Recipe_Material_ListDTO {
public Recipe_Material_ListDTO() {
		
	}
	private String rml_index;
	private String ml_no;
	private String ml_cnt;
	private String recipe_no;
	
	private String category="recipe_material_list";
	
	public String getCategory() {
		return category;
	}
	public String getrml_index() {
		return rml_index;
	}
	public void setrml_index(String rml_index) {
		this.rml_index = rml_index;
	}
	public String getMl_no() {
		return ml_no;
	}
	public void setMl_no(String ml_no) {
		this.ml_no = ml_no;
	}
	public String getMl_cnt() {
		return ml_cnt;
	}
	public void setMl_cnt(String ml_cnt) {
		this.ml_cnt = ml_cnt;
	}
	public String getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}
	@Override
	public String toString() {
		return "Recipe_Material_List [rml_index=" + rml_index + ", ml_no=" + ml_no + ", ml_cnt=" + ml_cnt
				+ ", recipe_no=" + recipe_no + "]";
	}
	public Recipe_Material_ListDTO(String rml_index, String ml_no, String ml_cnt, String recipe_no) {
		super();
		this.rml_index = rml_index;
		this.ml_no = ml_no;
		this.ml_cnt = ml_cnt;
		this.recipe_no = recipe_no;
	}
	
	

}
