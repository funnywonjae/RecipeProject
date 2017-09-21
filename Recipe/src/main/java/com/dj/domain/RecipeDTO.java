package com.dj.domain;

import java.sql.Date;

public class RecipeDTO {
	
public RecipeDTO() {
		
	}
	private String recipe_no;
	private String recipe_index;
	private String usr_id;
	private String recipe_content;
	private int  recipe_isdelete;
	private Date recipe_writedate;
	private String recipe_title;
	private String cg_name;
	private String cg_id;
	
	private String thumbnail_index;
	private int recipe_like;
	private String category="recipe";
	
	public String getCategory() {
		return category;
	}	
	
	public String getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}
	public String getRecipe_index() {
		return recipe_index;
	}
	public void setRecipe_index(String recipe_index) {
		this.recipe_index = recipe_index;
	}
	public String getusr_id() {
		return usr_id;
	}
	public void setusr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getRecipe_content() {
		return recipe_content;
	}
	public void setRecipe_content(String recipe_content) {
		this.recipe_content = recipe_content;
	}
	public int getRecipe_isdelete() {
		return recipe_isdelete;
	}
	public void setRecipe_isdelete(int recipe_isdelete) {
		this.recipe_isdelete = recipe_isdelete;
	}
	public Date getRecipe_writedate() {
		return recipe_writedate;
	}
	public void setRecipe_writedate(Date recipe_writedate) {
		this.recipe_writedate = recipe_writedate;
	}
	public String getRecipe_title() {
		return recipe_title;
	}
	public void setRecipe_title(String recipe_title) {
		this.recipe_title = recipe_title;
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
	
	
	public String getThumbnail_index() {
		return thumbnail_index;
	}
	public void setThumbnail_index(String thumbnail_index) {
		this.thumbnail_index = thumbnail_index;
	}
	public int getRecipe_like() {
		return recipe_like;
	}
	public void setRecipe_like(int recipe_like) {
		this.recipe_like = recipe_like;
	}

	@Override
	public String toString() {
		return "RecipeDTO [recipe_no=" + recipe_no + ", recipe_index=" + recipe_index + ", usr_id=" + usr_id
				+ ", recipe_content=" + recipe_content + ", recipe_isdelete=" + recipe_isdelete + ", recipe_writedate="
				+ recipe_writedate + ", recipe_title=" + recipe_title + ", cg_name=" + cg_name + ", cg_id=" + cg_id
				+ ", thumbnail_index=" + thumbnail_index + ", recipe_like=" + recipe_like
				+ ", category=" + category + "]";
	}

	public RecipeDTO(String recipe_no, String recipe_index, String usr_id, String recipe_content, int recipe_isdelete,
			 String recipe_title, String cg_name, String cg_id) {
		super();
		this.recipe_no = recipe_no;
		this.recipe_index = recipe_index;
		this.usr_id = usr_id;
		this.recipe_content = recipe_content;
		this.recipe_isdelete = recipe_isdelete;
		this.recipe_title = recipe_title;
		this.cg_name = cg_name;
		this.cg_id = cg_id;


	}

	
	
	

}
