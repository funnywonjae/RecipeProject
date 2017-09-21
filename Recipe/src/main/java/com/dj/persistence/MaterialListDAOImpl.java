package com.dj.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dj.domain.Material_ListDTO;
import com.dj.domain.Recipe_ListDTO;
@Repository
public class MaterialListDAOImpl implements MaterialListDAO {
   @Inject
   private SqlSession session;
   private static String namespace = "com.dj.Mappers.materialListMapper";
   //����Ʈ ������ �޾ƿ���-�ڿ���0828
   @Override
   public List<Material_ListDTO> MaterialListload() throws Exception {
      // TODO Auto-generated method stub
      return session.selectList(namespace+".materialListLoad");
   }

   @Override
   public int regist(Recipe_ListDTO dto) throws Exception {
      // TODO Auto-generated method stub
      return session.insert(namespace+".materialListRegist",dto);
   }

   @Override
   public void update(Recipe_ListDTO dto) throws Exception {
      // TODO Auto-generated method stub
      session.selectOne(namespace+".materialListDatachange",dto);
   }

   @Override
   public void delete(Recipe_ListDTO dto) throws Exception {
      // TODO Auto-generated method stub
      session.selectOne(namespace+".materialListDataDelete",dto);
   }

}