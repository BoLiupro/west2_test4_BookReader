package com.example.west2_test4.Dao;

import com.example.west2_test4.entity.Fiction;

import java.util.List;

public interface FictionDao {
    public List<Fiction> selectFiction();
    public Fiction selectOneFiction(int id);
}
