package com.dj.persistence;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dj.domain.UserInfoDTO;

public interface UserInfoDAO {

	//로그인
	public boolean loginUser(UserInfoDTO dto) throws Exception;
	
	//유저정보
	public UserInfoDTO viewUser(UserInfoDTO dto) throws Exception;
	
	//로그아웃
	public void logout(HttpSession session) throws Exception;
	
	//비번찾기 체크
	public boolean findPw(UserInfoDTO dto) throws Exception;
	
	//비번 리턴
	public String rePw(UserInfoDTO dto) throws Exception;

	// 유저 닉네임 중복체크
	public int chkusr_id(UserInfoDTO dto) throws Exception;

	// 유저 이메일 중복 체크
	public int chkusr_name(UserInfoDTO dto) throws Exception;

	public int checkID(String usr_id) throws Exception;

	public UserInfoDTO userCheck(UserInfoDTO dto) throws Exception;

	public void regist(UserInfoDTO dto) throws Exception;

	public int update(UserInfoDTO dto) throws Exception;

	public int delUser(UserInfoDTO dto) throws Exception;

	public void updateBookmark(UserInfoDTO dto) throws Exception;
}
