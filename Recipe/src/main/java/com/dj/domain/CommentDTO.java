package com.dj.domain;

import java.sql.Date;

public class CommentDTO {
	public CommentDTO() {
		
	}
	
	private String com_index;
	private String com_content;
	private int com_isdelete;
	private Date com_writedate;
	private String usr_id;
	private String com_password;
	private String recipe_no;
	
	private String category="comment";
	
	public String getCategory() {
		return category;
	}
	
	public String getCom_index() {
		return com_index;
	}
	public void setCom_index(String com_index) {
		this.com_index = com_index;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	public int getCom_isdelete() {
		return com_isdelete;
	}
	public void setCom_isdelete(int com_isdelete) {
		this.com_isdelete = com_isdelete;
	}
	public Date getCom_writedate() {
		return com_writedate;
	}
	public void setCom_writedate(Date com_writedate) {
		this.com_writedate = com_writedate;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getCom_password() {
		return com_password;
	}
	public void setCom_password(String com_password) {
		this.com_password = com_password;
	}
	public String getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}
	@Override
	public String toString() {
		return "Comment [com_index=" + com_index + ", com_content=" + com_content + ", com_isdelete=" + com_isdelete
				+ ", com_writedate=" + com_writedate + ", usr_id=" + usr_id + ", com_password=" + com_password
				+ ", recipe_no=" + recipe_no + "]";
	}
	public CommentDTO(String com_index,String com_content, int com_isdelete, String usr_id,
			String com_password, String recipe_no) {
		this.com_index = com_index;
		this.com_content = com_content;
		this.com_isdelete = com_isdelete;
		this.usr_id = usr_id;
		this.com_password = com_password;
		this.recipe_no = recipe_no;
	}
	

}
