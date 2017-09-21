package com.dj.domain;

public class UserInfoDTO {
	private String category="userinfo";
	private String usr_id, usr_pwd, usr_index, usr_name,usr_bookmark;
	private int usr_isdelete;
	
	//임시적으로 스트링 (값을 임의입력하려고)
	private String usr_registdate;

	
	public String getUsr_bookmark() {
		return usr_bookmark;
	}

	public void setUsr_bookmark(String usr_bookmark) {
		this.usr_bookmark = usr_bookmark;
	}

	public String getcategory(){
		return category;
	}
	
	public String getUsr_id() {
		return usr_id;
	}

	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}

	public String getUsr_pwd() {
		return usr_pwd;
	}

	public void setUsr_pwd(String usr_pwd) {
		this.usr_pwd = usr_pwd;
	}

	public String getUsr_index() {
		return usr_index;
	}

	public void setUsr_index(String usr_index) {
		this.usr_index = usr_index;
	}


	public String getUsr_name() {
		return usr_name;
	}

	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}


	public int getUsr_isdelete() {
		return usr_isdelete;
	}

	public void setUsr_isdelete(int usr_isdelete) {
		this.usr_isdelete = usr_isdelete;
	}

	public String getUsr_registdate() {
		return usr_registdate;
	}

	public void setUsr_registdate(String usr_registdate) {
		this.usr_registdate = usr_registdate;
	}

	public UserInfoDTO() {

	}

	@Override
	public String toString() {
		return "UserInfoDTO [category=" + category + ", usr_id=" + usr_id + ", usr_pwd=" + usr_pwd + ", usr_index="
				+ usr_index + ", usr_name=" + usr_name + ", usr_bookmark=" + usr_bookmark + ", usr_isdelete="
				+ usr_isdelete + ", usr_registdate=" + usr_registdate + "]";
	}

	public UserInfoDTO(String usr_id, String usr_pwd, String usr_index, String usr_name,
			String usr_bookmark, int usr_isdelete, String usr_registdate) {
		super();
		this.usr_id = usr_id;
		this.usr_pwd = usr_pwd;
		this.usr_index = usr_index;
		this.usr_name = usr_name;
		this.usr_bookmark = usr_bookmark;
		this.usr_isdelete = usr_isdelete;
		this.usr_registdate = usr_registdate;
	}

	
}