package com.example.west2_test4.Dao;

import com.example.west2_test4.entity.User;

import java.util.List;

public interface UserDao {
    public int insertUser(User user);
    public int deleteUser(int id);
    public int updateUser(User user);
    public List<User> selectUser();
    public User selectOneUser(int id);
}
