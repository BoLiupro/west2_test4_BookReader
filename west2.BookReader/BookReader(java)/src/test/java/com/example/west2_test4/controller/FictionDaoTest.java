package com.example.west2_test4.controller;

import com.example.west2_test4.Dao.FictionDao;
import com.example.west2_test4.entity.Fiction;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FictionDaoTest {

    @Test
    public void selectFiction() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(is);
        SqlSession SqlSession =factory.openSession();
        FictionDao fictionDao=SqlSession.getMapper(FictionDao.class);
        List<Fiction> fictions =fictionDao.selectFiction();
        SqlSession.commit();
        for(Fiction i:fictions){
            System.out.println(i.getPicture_path());
        }

    }
}