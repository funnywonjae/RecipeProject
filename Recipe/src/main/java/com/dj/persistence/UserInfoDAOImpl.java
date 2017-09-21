package com.dj.persistence;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.dj.domain.UserInfoDTO;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

	private static final Logger logger = Logger.getLogger(UserInfoDAOImpl.class);

	@Inject
	private SqlSession session;
	private static String namespace = "com.dj.Mappers.UserInfoMapper";

	// 아이디 체크 DTO
	@Override
	public int chkusr_id(UserInfoDTO dto) throws Exception {
		return session.selectOne(namespace + ".chkusr_id", dto);

	}

	// 닉네임 체크 DTO
	@Override
	public int chkusr_name(UserInfoDTO dto) throws Exception {
		return session.selectOne(namespace + ".chkusr_name", dto);
	}

	@Override
	public void updateBookmark(UserInfoDTO dto) throws Exception {

		session.selectOne(namespace + ".userBookmarkadd", dto);

	}

	@Override
	public int checkID(String usr_id) throws Exception {
		return session.selectOne(namespace + ".checkUserId", usr_id);
	}

	@Override
	public UserInfoDTO userCheck(UserInfoDTO dto) throws Exception {

		return session.selectOne(namespace + ".userLogin", dto);
	}

	@Override
	public void regist(UserInfoDTO dto) throws Exception {

		session.selectOne(namespace + ".userRegist", dto);

	}

	@Override
	public int update(UserInfoDTO dto) throws Exception {
		return session.selectOne(namespace + ".userUpdate", dto);
	}

	@Override
	public int delUser(UserInfoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		logger.info(session.selectOne(namespace + ".userDelete", dto));
		return 123;
	}

	// 로그인
	@Override
	public boolean loginUser(UserInfoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		int name = session.selectOne(namespace + ".loginUser", dto);
		return (name == 1) ? true : false;

	}

	// 유저정보
	@Override
	public UserInfoDTO viewUser(UserInfoDTO dto) throws Exception {
		// TODO Auto-generated method stub

		return session.selectOne(namespace + ".viewUser", dto);
	}

	// 로그아웃
	@Override
	public void logout(HttpSession session) throws Exception {
		// TODO Auto-generated method stub

	}
	// 비번찾기
	@Override
	public boolean findPw(UserInfoDTO dto) throws Exception {
		// TODO Auto-generated method stub
		int name = session.selectOne(namespace + ".findPw", dto);
		return (name == 1) ? true : false;

		
	}

	//비번 리턴
	@Override
	public String rePw(UserInfoDTO dto) throws Exception {


		return session.selectOne(namespace + ".rePw", dto);
	}
	
}
