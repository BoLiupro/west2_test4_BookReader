package com.example.west2_test4.Dao;
import com.example.west2_test4.entity.Fiction;
import com.example.west2_test4.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class sql_Methods {

    public static List<User> selectUser() throws IOException {
        //===================================================================
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(is);
        SqlSession SqlSession =factory.openSession();
        UserDao userDao=SqlSession.getMapper(UserDao.class);
        List<User> user =userDao.selectUser();
        SqlSession.commit();
        //===================================================================
        return user;
    }

    public static User selectOneUser(int id) throws IOException {
        //===================================================================
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(is);
        SqlSession SqlSession =factory.openSession();
        UserDao userDao=SqlSession.getMapper(UserDao.class);
        User user =userDao.selectOneUser(id);
        SqlSession.commit();
        //===================================================================
        return user;
    }

    public static void insertUser(int new_id ,String new_password) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(is);
        SqlSession SqlSession =factory.openSession();
        UserDao userDao=SqlSession.getMapper(UserDao.class);
        int i =userDao.insertUser(new User(new_id,new_password,0,null,null));
        SqlSession.commit();
    }

    public static void updateUser(int id,String password,int randCode,String collection,String history) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(is);
        SqlSession SqlSession =factory.openSession();
        UserDao userDao=SqlSession.getMapper(UserDao.class);
        int i =userDao.updateUser(new User(id,password,randCode,collection,history));
        SqlSession.commit();
    }

        public static List<Fiction> selectFiction() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession SqlSession = factory.openSession();
        FictionDao fictionDao = SqlSession.getMapper(FictionDao.class);
        List<Fiction> fictions = fictionDao.selectFiction();
        SqlSession.commit();
        return fictions;
    }

    public static Fiction selectOneFiction(int id) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession SqlSession = factory.openSession();
        FictionDao fictionDao = SqlSession.getMapper(FictionDao.class);
        Fiction fiction = fictionDao.selectOneFiction(id);
        SqlSession.commit();
        return fiction;
    }
}
