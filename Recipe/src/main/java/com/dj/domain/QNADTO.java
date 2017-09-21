package com.dj.domain;

import java.sql.Date;

public class QNADTO {
	
public QNADTO() {
		
	}
	private String qna_index;
	private String qna_content;
	private Date qna_writedate;
	private int qna_grpno;
	private int qna_depth;
	private String usr_id;
	private int qna_isdelete;
	private String recipe_no;
	private int type;
	
	private String category="qna";
	
	public String getCategory() {
		return category;
	}
	
	public String getQna_index() {
		return qna_index;
	}
	public void setQna_index(String qna_index) {
		this.qna_index = qna_index;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Date getQna_writedate() {
		return qna_writedate;
	}
	public void setQna_writedate(Date qna_writedate) {
		this.qna_writedate = qna_writedate;
	}
	public int getQna_grpno() {
		return qna_grpno;
	}
	public void setQna_grpno(int qna_grpno) {
		this.qna_grpno = qna_grpno;
	}
	public int getQna_depth() {
		return qna_depth;
	}
	public void setQna_depth(int qna_depth) {
		this.qna_depth = qna_depth;
	}
	public String getusr_id() {
		return usr_id;
	}
	public void setusr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public int getQna_isdelete() {
		return qna_isdelete;
	}
	public void setQna_isdelete(int qna_isdelete) {
		this.qna_isdelete = qna_isdelete;
	}
	public String getRecipe_no() {
		return recipe_no;
	}
	public void setRecipe_no(String recipe_no) {
		this.recipe_no = recipe_no;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Qna [qna_index=" + qna_index + ", qna_content=" + qna_content + ", qna_writedate=" + qna_writedate
				+ ", qna_grpno=" + qna_grpno + ", qna_depth=" + qna_depth + ", usr_id=" + usr_id + ", qna_isdelete="
				+ qna_isdelete + ", recipe_no=" + recipe_no + "]";
	}
	public QNADTO(String qna_index, String qna_content, 
			int qna_grpno, int qna_depth, String usr_id,
			int qna_isdelete, String recipe_no,int type) {
		this.qna_index = qna_index;
		this.qna_content = qna_content;
		this.qna_writedate = qna_writedate;
		this.qna_grpno = qna_grpno;
		this.qna_depth = qna_depth;
		this.usr_id = usr_id;
		this.qna_isdelete = qna_isdelete;
		this.recipe_no = recipe_no;
		this.type=type;
	}
	

}
