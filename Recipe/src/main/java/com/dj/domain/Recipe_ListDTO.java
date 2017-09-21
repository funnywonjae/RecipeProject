package com.dj.domain;

//레시피 리스트 검색용으로 변경 -박영태-0828
public class Recipe_ListDTO {
	private String searchtype="all";
	private String keyword;
public Recipe_ListDTO() {

}
	
	public String getSearchType() {
		return searchtype;
	}
	public void setSearchType(String searchtype) {
		this.searchtype = searchtype;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Recipe_ListDTO(String searchtype, String keyword) {
	
		this.searchtype = searchtype;
		this.keyword = keyword;
	}

	public Recipe_ListDTO(String keyword) {
	
		this.keyword = keyword;
		this.searchtype="all";
	}

	@Override
	public String toString() {
		return "Recipe_ListDTO [searchtype=" + searchtype + ", keyword=" + keyword + "]";
	}
	
	
	
	
	
 
}
