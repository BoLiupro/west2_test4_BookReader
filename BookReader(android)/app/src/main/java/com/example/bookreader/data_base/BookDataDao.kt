package com.example.bookreader.data_base

import androidx.room.*
import com.example.bookreader.data_class.BookData

@Dao
interface BookDataDao {
    @Insert
    fun insertBookData(bookData: BookData): Long

    @Update
    fun updateBookData(newBookData: BookData)

    @Query("select * from BookData")
    fun loadAllBookData(): List<BookData>

    @Delete
    fun deleteBookData(bookData: BookData)
}