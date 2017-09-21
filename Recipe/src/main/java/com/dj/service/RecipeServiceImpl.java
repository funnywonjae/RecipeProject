package com.dj.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dj.domain.FileListDTO;
import com.dj.domain.RecipeDTO;
import com.dj.domain.Recipe_Material_ListDTO;
import com.dj.domain.ThumbnailDTO;
import com.dj.persistence.FileListDAO;
import com.dj.persistence.Index_TableDAO;
import com.dj.persistence.RecipeDAO;
import com.dj.persistence.RecipeMaterialListDAO;
import com.dj.persistence.ThumbnailDAO;

@Service
public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	@Inject
	FileListDAO filelistDAO;
	@Inject
	RecipeDAO recipeDAO;
	@Inject
	Index_TableDAO index_tableDAO;
	@Inject
	ThumbnailDAO thumbnailDAO;
	@Inject
	RecipeMaterialListDAO rmlDAO;
	
	
	
	
	@Override
	public void modMaterial(Recipe_Material_ListDTO rmlDTO) throws Exception {
		
		logger.info("modMaterial_DTO="+rmlDTO.toString());

		rmlDAO.update(rmlDTO);
	}
	
	
	@Override
	public void setMaterial(Recipe_Material_ListDTO rmlDTO) throws Exception {
		
		logger.info("A"+rmlDTO.toString());
		
		String index = index_tableDAO.readTableIndex("recipe");
		rmlDTO.setrml_index(index);
		rmlDTO.setRecipe_no(""+Long.parseLong(index.substring(4,index.length()), 16));
		logger.info("B"+rmlDTO.toString());
		rmlDAO.regist(rmlDTO);
		index_tableDAO.indexInc(rmlDTO.getCategory());
		
		

	}

	@Override
	public boolean modRecipe(RecipeDTO dto) throws Exception {

		int result = 0;

		try {
			result = recipeDAO.update(dto);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result > 0;
	}

	@Override
	public int delRecipe(String bno) throws Exception {
		return recipeDAO.delete(bno);

	}

	@Override
	public ThumbnailDTO readThumbnail(String no) throws Exception {
		return thumbnailDAO.getReadThumbnail(no);
	}

	@Override
	public Map<String, Object> readLimitRecipe(Map<String, Object> readData) throws Exception {
		List<RecipeDTO> recipe = new ArrayList<RecipeDTO>();
		readData.put("cnt", Integer.parseInt(readData.get("cnt").toString()));
		try {
			switch (readData.get("action").toString()){
			case "main":
				recipe= recipeDAO.getMainLimitRecipe(readData);
				break;
			case "search" : 
				recipe = recipeDAO.getSearchLimitRecipe(readData);
				logger.info(recipe.toString());
				break;
			case "bookmark":
				
				break;
			}
				


			

			if (recipe != null) {
				List<ThumbnailDTO> thumbnail = new ArrayList<ThumbnailDTO>();
				for (RecipeDTO list : recipe) {
					thumbnail.add(thumbnailDAO.getReadThumbnail(list.getRecipe_no()));
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("recipe", recipe);
				map.put("thumbnail", thumbnail);
				return map;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<RecipeDTO> readRandRecipe(String category) throws Exception {
		List<RecipeDTO> list = recipeDAO.getRandomRecipe(category);
		return list;
	}

	@Override
	public int setRecipe(RecipeDTO dto, String name) throws Exception {

		String index = index_tableDAO.readTableIndex("recipe");
		
		String no=(""+Long.parseLong(index.substring(4,index.length()), 16));
		dto = new RecipeDTO(no,index, name, dto.getRecipe_content(), 0,
				dto.getRecipe_title(), dto.getcg_name(), dto.getCg_id());
		
		int result = 0;
		try {
			System.out.println(dto);
			result = recipeDAO.regist(dto);
			logger.info("" + result);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	@Override
	public void modFile(HttpServletRequest request, HttpServletResponse response, MultipartFile upload, String category,
			String bno) throws Exception {
		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fileName = upload.getOriginalFilename();
		String type = null;
		System.out.println("fileName= " + fileName);

		try {
			Tika tika = new Tika();
			type = tika.detect(fileName);
			logger.info(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type.contains("image")) {
			try {

				// MimetypesFileTypeMap 을 활용한 타입 알아내기
				// 확실한 구분이 안되서 사용하지 않았음
				/*
				 * File f = new File(fileName); String type = new
				 * MimetypesFileTypeMap().getContentType(fileName);
				 * 
				 * System.out.println(type);
				 */
				/* 아파치 tika활용 타입알아내기 */

				byte[] bytes = upload.getBytes();
				/*
				 * String root_path =
				 * request.getSession().getServletContext().getRealPath("/");
				 * String attach_path = "resources/img/";*/


				String index = recipeDAO.getIndex(bno);
				index = index.substring(4,index.length());
				index = (""+Long.parseLong(index,16));
				
				String serverName = filelistDAO.modServerName(index);
					
				if (!((serverName.indexOf(".", 2)) >= 0)) {
					System.out.println("야야야야");
					serverName += fileName.substring(fileName.indexOf("."));
				} else {
					serverName = recipeDAO.getIndex(bno) + fileName.substring(fileName.indexOf(".", 2));
				}

				logger.info("serverName = " + serverName);
				String uploadPath = "c:/upload/";// 저장경로
				logger.info("경로 : " + uploadPath);

				String fileUrl = "/upload/";// url경로

				/*
				 * FileListDTO(String file_name,String file_server, String
				 * file_index, String file_path, String file_type,String
				 * recipe_no)
				 */

				// 썸네일과 게시판 이미지파일을 구분하기위해
				// 폴더 외에도 저장되는 이름을 다르게함
				serverName = "t" + serverName;
				out = new FileOutputStream(new File(uploadPath + "thumbnail/" + serverName));
				out.write(bytes);
				fileUrl += "thumbnail/" + serverName;

				/*
				 * String thumbnail_index, String thumbnail_server, String
				 * thumbnail_path, String recipe_no, String thumbnail_name
				 */

				ThumbnailDTO dto = new ThumbnailDTO(index, serverName, fileUrl, bno, fileName);
				logger.info(dto.toString());
				try {
					System.out.println(dto);
					thumbnailDAO.update(dto);

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (printWriter != null) {
						printWriter.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void setFile(HttpServletRequest request, HttpServletResponse response, MultipartFile upload, String category)
			throws Exception {

		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fileName = upload.getOriginalFilename();
		String type = null;

		try {
			Tika tika = new Tika();
			type = tika.detect(fileName);
			logger.info(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type.contains("image")) {
			try {

				// MimetypesFileTypeMap 을 활용한 타입 알아내기
				// 확실한 구분이 안되서 사용하지 않았음
				/*
				 * File f = new File(fileName); String type = new
				 * MimetypesFileTypeMap().getContentType(fileName);
				 * 
				 * System.out.println(type);
				 */
				/* 아파치 tika활용 타입알아내기 */

				byte[] bytes = upload.getBytes();
				/*
				 * String root_path =
				 * request.getSession().getServletContext().getRealPath("/");
				 * String attach_path = "resources/img/";
				 */
				System.out.println(category);
				String index = index_tableDAO.readTableIndex(category);
				String serverName = filelistDAO.modServerName(index);
					
					serverName += fileName.substring(fileName.indexOf("."));

				logger.info("serverName = " + serverName);
				String uploadPath = "c:/upload/";
				// 저장경로
				logger.info("경로 : " + uploadPath);

				String callback = request.getParameter("CKEditorFuncNum");

				printWriter = response.getWriter();
				String fileUrl = "/upload/";
				// url경로

				/*
				 * FileListDTO(String file_name,String file_server, String
				 * file_index, String file_path, String file_type,String
				 * recipe_no)
				 */
				
				if (category == "filelist") {
					out = new FileOutputStream(new File(uploadPath + serverName));
					out.write(bytes);
					fileUrl += serverName;// url경로
					FileListDTO dto = new FileListDTO(fileName, serverName, index, fileUrl, type,""
					+Long.parseLong(index_tableDAO.readTableIndex("recipe").substring(4,index_tableDAO.readTableIndex("recipe").length()), 16));

					try {
						filelistDAO.regist(dto);
						index_tableDAO.indexInc(category);
						printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
								+ callback + ",'" + "/upload/" + serverName + "','이미지를 업로드 하였습니다.'" + ")</script>");

						printWriter.flush();

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					// 썸네일과 게시판 이미지파일을 구분하기위해
					// 폴더 외에도 저장되는 이름을 다르게함
					serverName = "t" + serverName;
					out = new FileOutputStream(new File(uploadPath + "thumbnail/" + serverName));
					out.write(bytes);
					fileUrl += "thumbnail/" + serverName;

					/*
					 * String thumbnail_index, String thumbnail_server, String
					 * thumbnail_path, String recipe_no, String thumbnail_name
					 */

				
					ThumbnailDTO dto = new ThumbnailDTO(index, serverName, fileUrl, ""+Long.parseLong(index_tableDAO.readTableIndex("recipe").substring(4,index_tableDAO.readTableIndex("recipe").length()), 16), fileName);
					System.out.println("썸네일dtd = " + dto.getRecipe_no());
					try {

						thumbnailDAO.regist(dto);
						index_tableDAO.indexInc(category);
						index_tableDAO.indexInc("recipe");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (printWriter != null) {
						printWriter.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			printWriter = response.getWriter();
			printWriter.println("<script type='text/javascript'>alert('파일명에 '.'이 있거나 이미지 파일이 아닐경우 안됩니다.');</script>");
			printWriter.flush();

		}
	}

	@Override
	public Recipe_Material_ListDTO readMaterial(int no) throws Exception {
			
		
		return rmlDAO.RecipeMaterialListload(no);
		
	}
	
	@Override
	public RecipeDTO readRecipe(int i) {

		try {
			
			return recipeDAO.Recipeload(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
