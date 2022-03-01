package com.example.bookreader.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookreader.data_class.BookData

@Database(version = 1, entities = [BookData::class])
abstract class BookDataBase: RoomDatabase() {
    abstract fun bookDataDao(): BookDataDao
    companion object {
        private var instance: BookDataBase? = null
        @Synchronized
        fun getDatabase(context: Context): BookDataBase {//此处一定要用application context
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                BookDataBase::class.java, "app_database")
                .build().apply {
                    instance = this
                }
        }
    }
}