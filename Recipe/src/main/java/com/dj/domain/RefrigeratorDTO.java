package com.dj.domain;

import java.sql.Date;

public class RefrigeratorDTO {
	//¹Ú¿µÅÂ 0829
public RefrigeratorDTO() {
		
	}
	private String usr_id;
	private String ref_ml_no;
	private String ref_edate;
	private String ref_cnt;
	private String ref_index;
	private String category="refrigerator";

	public String getCategory() {
		return category;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getRef_Ml_no() {
		return ref_ml_no;
	}
	public void setMl_no(String ml_no) {
		this.ref_ml_no = ml_no;
	}
	public String getRef_edate() {
		return ref_edate;
	}
	public void setRef_edate(String ref_edate) {
		this.ref_edate = ref_edate;
	}
	public String getRef_cnt() {
		return ref_cnt;
	}
	public void setRef_cnt(String ref_cnt) {
		this.ref_cnt = ref_cnt;
	}
	
	public String getRef_index() {
		return ref_index;
	}
	public void setRef_index(String ref_index) {
		this.ref_index = ref_index;
	}
	
	@Override
	public String toString() {
		return "RefrigeratorDTO [usr_id=" + usr_id + ", ml_no=" + ref_ml_no + ", ref_edate=" + ref_edate + ", ref_cnt="
				+ ref_cnt + ", ref_index=" + ref_index + ", category=" + category + "]";
	}

	
	public RefrigeratorDTO(String usr_id, String mlno, String edate, String count) {
		super();
		this.usr_id = usr_id;
		this.ref_ml_no = mlno;
		this.ref_edate = edate;
		this.ref_cnt = count;
	}
	

}
