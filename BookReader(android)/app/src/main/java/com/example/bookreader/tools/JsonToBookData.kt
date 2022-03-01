package com.example.bookreader.tools

import android.util.Log
import com.example.bookreader.data_class.BookData
import com.google.gson.JsonObject
import java.lang.Error

object JsonToBookData {
    fun shortJsonToBookDataList(jsonList: ArrayList<JsonObject>): ArrayList<BookData>{
        val bookList = ArrayList<BookData>()
        for(json in jsonList){
            val book = BookData(
                json.get("id").toString().replace("\"","").toLong(),
                json.get("bookName").toString().replace("\"",""),
                json.get("author").toString().replace("\"",""),
                json.get("coverUrl").toString().replace("\"",""),
                json.get("intro").toString().replace("\"",""),
                json.get("rate").toString().replace("\"","").toFloat(),
                json.get("isCollected").toString().replace("\"","").toBoolean(),
                "空值"
            )
            bookList.add(book)
        }
        return bookList
    }

    fun longJsonToBookData(jsonList: ArrayList<JsonObject>): ArrayList<BookData>{
        val bookList = ArrayList<BookData>()
        try{
            for(json in jsonList){
                val book = BookData(
                    json.get("id").toString().replace("\"","").toLong(),
                    json.get("bookName").toString().replace("\"",""),
                    json.get("author").toString().replace("\"",""),
                    json.get("coverUrl").toString().replace("\"",""),
                    json.get("intro").toString().replace("\"",""),
                    json.get("rate").toString().replace("\"","").toFloat(),
                    json.get("isCollected").toString().replace("\"","").toBoolean(),
                    json.get("tag").toString().replace("\"","")
                )
                bookList.add(book)
            }
        }catch (e: Error){
            Log.e("JsonToBookData:", "having problem converting json to book data list!")
        }
        return bookList
    }
}

