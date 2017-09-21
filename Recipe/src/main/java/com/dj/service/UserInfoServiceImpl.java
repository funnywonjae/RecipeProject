package com.dj.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.dj.domain.RecipeDTO;
import com.dj.domain.RefrigeratorDTO;
import com.dj.domain.UserInfoDTO;
import com.dj.persistence.Index_TableDAO;
import com.dj.persistence.RecipeDAO;
import com.dj.persistence.UserInfoDAO;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Inject 
	UserInfoDAO userInfoDao;
	@Inject
	Index_TableDAO index_TableDao;
	@Inject
	RefrigeratorService refService;
	@Inject 
	RecipeDAO recipe;
	/*@Inject	ModIndex modIndex;*/
	@Inject
	SearchService searchService;

	@Override
	public void delBookmarkList(String bno,UserInfoDTO dto) throws Exception {
		String[] i=dto.getUsr_bookmark().split(",");
		String m="";
		for(String x:i) {
			if(x.equals(bno)) {
				continue;
			}
				System.out.println("222"+x);
			if(m==""){
				m=x;
			}else{
				m=m+","+x;
			}
		}
		System.out.println("111"+m);
		
		dto.setUsr_bookmark(m);
		System.out.println("555"+m);
		System.out.println("333"+dto);
		this.setAddBookmarkRecipe(dto);
		
	}

	@Override
	public int chkusr_id(UserInfoDTO dto) throws Exception {
		System.out.println(dto);
  		return userInfoDao.chkusr_id(dto);
 	}
	//�쉶�썝媛��엯 �땳�꽕�엫泥댄겕 �꽌鍮꾩뒪 
	@Override
	public int chkusr_name(UserInfoDTO dto) throws Exception {
  		return userInfoDao.chkusr_name(dto);
 	}
	@Override
	public void setAddBookmarkRecipe(UserInfoDTO dto) throws Exception {
		System.out.println(dto);
		userInfoDao.updateBookmark(dto);
			
	}
	@Override
	public List<RecipeDTO> getBookmarkRecipe(String[] bookmark) throws Exception {
		List<RecipeDTO> list = new ArrayList<RecipeDTO>();
		
		for(String a : bookmark){
			RecipeDTO dto = new RecipeDTO();
			dto.setRecipe_no(a);
			list.add(dto);
		}
		
		return recipe.BookmarkRecipeload(list);
	}	
	
	
	
	
	@Override
	public int delInfo(UserInfoDTO dto) throws Exception {
		return userInfoDao.delUser(dto);
	}
	
	
@Override
public int modInfo(UserInfoDTO dto) throws Exception {

	return userInfoDao.update(dto);
}
	
@Override
public int checkUserId(String usr_id) throws Exception {
	int x =111;
	try {
		System.out.println("�뿬湲곕룄 �븘�땲�빞");
		x = userInfoDao.checkID(usr_id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return x;
}


	@Override
	public UserInfoDTO checkUserIdWithPwd(UserInfoDTO dto) throws Exception{
		
		return userInfoDao.userCheck(dto);
		
	}

	/*@Override
	public String getTableIndex(Index_TableDTO dto) throws Exception {
		System.out.println("AA"+dto.getCategory());
		return IndexDao.tableIndex(dto);
		
	}*/
	@Override
	public synchronized void signup(UserInfoDTO dto) throws Exception {
		dto.setUsr_index(index_TableDao.readTableIndex(dto.getcategory()));
		userInfoDao.regist(dto);
		index_TableDao.indexInc(dto.getcategory());
		
	}
	

	@Override
	public RecipeDTO getRecipeList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	// 濡쒓렇�씤�쓣 �쐞�븳 id, pwd 媛� 鍮꾧탳�썑 �엳�쑝硫� 李몄쓣 由ы꽩�븿

		@Override
		public boolean loginUser(UserInfoDTO dto, HttpSession httpSession, HttpServletRequest request,
				HttpServletResponse response) {
			// TODO Auto-generated method stub

			try {
				boolean result = userInfoDao.loginUser(dto);

				// session�뿉 �븘�씠�뵒瑜� ���옣�븿
				if (result) {
					
					httpSession = request.getSession();
					httpSession.setAttribute("sessionId", dto.getUsr_id());
			
					httpSession.setAttribute("userInfo", userInfoDao.viewUser(dto));
					
					if(refService.readRefWithId(dto.getUsr_id())!=null){
					httpSession.setAttribute("rdto", refService.readRefWithId(dto.getUsr_id()));
					
					RefrigeratorDTO rdto = (RefrigeratorDTO)httpSession.getAttribute("rdto");
					List<String> my_ml_no = new ArrayList<String>();
					List<String> my_ml_cnt = new ArrayList<String>();
					List<String> my_ml_edate = new ArrayList<String>();
					
					
					for(String a : rdto.getRef_Ml_no().split(",")){
						my_ml_no.add(a);
					}
					for(String a : rdto.getRef_cnt().split(",")){
						my_ml_cnt.add(a);
					}
					for(String a : rdto.getRef_edate().split(",")){
						my_ml_edate.add(a);
					}
					
					httpSession.setAttribute("my_ml_no",my_ml_no);
					httpSession.setAttribute("my_ml_cnt",my_ml_cnt);
					httpSession.setAttribute("my_ml_edate",my_ml_edate);
					
					}else{
						httpSession.setAttribute("rdto",null);	
					}
				      

					System.out.println();
					
				}
				return result;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return false;
		}

		// 유저정보
		@Override
		public UserInfoDTO viewUser(UserInfoDTO dto) throws Exception {
			// TODO Auto-generated method stub
			return userInfoDao.viewUser(dto);
		}

		// 세션 종료하고 로그아웃
		@Override
		public void logout(HttpSession session) throws Exception {
			// TODO Auto-generated method stub
			String saveId = (String)session.getAttribute("saveId");
			String sessionId = (String)session.getAttribute("sessionId");
			session.removeAttribute("my_ml_no");
			session.removeAttribute("sessionId");
//			session.invalidate();
			

		}
		
		//비번찾기 체크
		@Override
		public boolean findPw(UserInfoDTO dto) throws Exception {
			// TODO Auto-generated method stub
			System.out.println(dto);
			boolean result = userInfoDao.findPw(dto);
			if(result){

				return true;
			}
			return false;
		}
		
		//비번 리턴
		@Override
		public String rePw(UserInfoDTO dto) throws Exception {
			// TODO Auto-generated method stub

			return userInfoDao.rePw(dto);
		}
		
		
		@Override
		public Map<String,Object> getBookmarkList(UserInfoDTO dto) throws Exception {
			
			
			
			
			List <RecipeDTO> bookmarklist=new ArrayList<RecipeDTO>();
			if(dto!=null){
			if(dto.getUsr_bookmark()!=null){
			String [] i=(dto.getUsr_bookmark().split(","));
			bookmarklist.addAll(searchService.getBookmarkRecipe(i));
			}
			}
			String stringbookmark=dto.getUsr_bookmark();
			List<String> bookmark = new ArrayList<String>();
			if(dto.getUsr_bookmark()!=null){
			for(String a : stringbookmark.split(",")){
				bookmark.add(a);
			}
			}
			Map<String,Object> bookmarks = new HashMap<String,Object>();
			//recipe媛앹껜媛� 紐⑤몢 �떞寃⑥엳�뒗 由ъ뒪�듃���엯 媛앹껜
			bookmarks.put("bookmarklist",bookmarklist);
			//recipe踰덊샇媛� 紐⑤몢 �떞寃⑥엳�뒗 由ъ뒪�듃
			bookmarks.put("bookmark",bookmark);
			
			return bookmarks;
		}
		@Override
		public void addBookmarkList(String bno,UserInfoDTO dto) throws Exception {
		
			String i=null;
			if(dto.getUsr_bookmark()==null){
				i=bno;
			}else {
			i=dto.getUsr_bookmark()+","+bno;
					}
			
			dto.setUsr_bookmark(i);
			System.out.println(dto);
			this.setAddBookmarkRecipe(dto);
			
		}
}
