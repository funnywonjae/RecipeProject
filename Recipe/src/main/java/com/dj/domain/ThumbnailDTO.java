package com.dj.domain;

public class ThumbnailDTO {
	private String category="thumbnail";

private String thumbnail_index;
private String thumbnail_server;
private String thumbnail_path;
private String recipe_no;
private String thumbnail_name;



public ThumbnailDTO() {
	
}


public String getRecipe_no() {
	return recipe_no;
}


public void setRecipe_no(String recipe_no) {
	this.recipe_no = recipe_no;
}


public String getThumbnail_name() {
	return thumbnail_name;
}


public void setThumbnail_name(String thumbnail_name) {
	this.thumbnail_name = thumbnail_name;
}


public String getCategory() {
	return category;
}


public String getThumbnail_index() {
	return thumbnail_index;
}
public void setThumbnail_index(String thumbnail_index) {
	this.thumbnail_index = thumbnail_index;
}
public String getThumbnail_server() {
	return thumbnail_server;
}
public void setThumbnail_server(String thumbnail_server) {
	this.thumbnail_server = thumbnail_server;
}
public String getThumbnail_path() {
	return thumbnail_path;
}
public void setThumbnail_path(String thumbnail_path) {
	this.thumbnail_path = thumbnail_path;
}


@Override
public String toString() {
	return "ThumbnailDTO [category=" + category + ", thumbnail_index=" + thumbnail_index + ", thumbnail_server="
			+ thumbnail_server + ", thumbnail_path=" + thumbnail_path + ", recipe_no=" + recipe_no + ", thumbnail_name="
			+ thumbnail_name + "]";
}


public ThumbnailDTO(String thumbnail_index, String thumbnail_server, String thumbnail_path, String recipe_no,
		String thumbnail_name) {
	this.thumbnail_index = thumbnail_index;
	this.thumbnail_server = thumbnail_server;
	this.thumbnail_path = thumbnail_path;
	this.recipe_no = recipe_no;
	this.thumbnail_name = thumbnail_name;
	this.thumbnail_path = thumbnail_path;
}


}
