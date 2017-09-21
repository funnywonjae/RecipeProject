package com.dj.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dj.domain.RecipeDTO;
import com.dj.domain.UserInfoDTO;

public interface UserInfoService {
	// 회원가입 id체크 서비스
	public int chkusr_id(UserInfoDTO dto) throws Exception;

	// 회원가입 닉네임체크 서비스
	public int chkusr_name(UserInfoDTO dto) throws Exception;

	public int checkUserId(String usr_id) throws Exception;

	public UserInfoDTO checkUserIdWithPwd(UserInfoDTO dto) throws Exception;

	public void signup(UserInfoDTO dto) throws Exception;

	// public String getTableIndex(Index_TableDTO dto) throws Exception;
	public RecipeDTO getRecipeList() throws Exception;

	public int modInfo(UserInfoDTO dto) throws Exception;

	public int delInfo(UserInfoDTO dto) throws Exception;

	public List<RecipeDTO> getBookmarkRecipe(String[] bookmark) throws Exception;

	public void setAddBookmarkRecipe(UserInfoDTO dto) throws Exception;

	//로그인
	public boolean loginUser(UserInfoDTO dto, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	//유저정보
	public UserInfoDTO viewUser(UserInfoDTO dto) throws Exception;
	
	//로그아웃
	public void logout(HttpSession session) throws Exception;
	
	//비번찾기체크
	public boolean findPw(UserInfoDTO dto) throws Exception;
	
	//비번 리턴
	public String rePw(UserInfoDTO dto) throws Exception;
	
	//북마크 리스트타입으로 리턴받음
	public Map<String,Object> getBookmarkList(UserInfoDTO dto) throws Exception;
	//북마크 추가
	public void addBookmarkList(String bno,UserInfoDTO dto) throws Exception;
	//	북마크 삭제
	public void  delBookmarkList(String bno,UserInfoDTO dto) throws Exception;
}
