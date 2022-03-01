package com.example.bookreader.data_base;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H\'\u00a8\u0006\f"}, d2 = {"Lcom/example/bookreader/data_base/BookDataDao;", "", "deleteBookData", "", "bookData", "Lcom/example/bookreader/data_class/BookData;", "insertBookData", "", "loadAllBookData", "", "updateBookData", "newBookData", "app_debug"})
public abstract interface BookDataDao {
    
    @androidx.room.Insert()
    public abstract long insertBookData(@org.jetbrains.annotations.NotNull()
    com.example.bookreader.data_class.BookData bookData);
    
    @androidx.room.Update()
    public abstract void updateBookData(@org.jetbrains.annotations.NotNull()
    com.example.bookreader.data_class.BookData newBookData);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from BookData")
    public abstract java.util.List<com.example.bookreader.data_class.BookData> loadAllBookData();
    
    @androidx.room.Delete()
    public abstract void deleteBookData(@org.jetbrains.annotations.NotNull()
    com.example.bookreader.data_class.BookData bookData);
}