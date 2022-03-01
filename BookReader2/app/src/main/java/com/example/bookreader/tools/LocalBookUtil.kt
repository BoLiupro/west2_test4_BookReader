package com.example.bookreader.tools

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bookreader.MyApplication
import com.example.bookreader.ReaderActivity
import com.example.bookreader.data_base.BookDataBase
import com.example.bookreader.data_class.BookData
import com.example.bookreader.main_fragments.BaseFragment
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

object LocalBookUtil {
    private val bookDataDao = BookDataBase.getDatabase(MyApplication.context).bookDataDao()
    fun downloadBook(bookData: BookData, activityForToast: Activity){
        Toast.makeText(MyApplication.context, "下载", Toast.LENGTH_SHORT).show()
        var bookContent = "下载过程中失败了没有重试！请尝试重新下载！"

        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<ResponseBody> = webService.downloadBook(bookData.id.toInt())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                val responseBody = response.body()
                if (responseBody != null){
                    bookContent = responseBody.string()
                    bookData.content = bookContent
                    bookData.lastReadTime = "0"
                    thread {
                        try{
                            bookDataDao.insertBookData(bookData)
                        }catch (e: SQLiteConstraintException){
                            bookDataDao.updateBookData(bookData)
                            activityForToast.runOnUiThread {
                                Toast.makeText(MyApplication.context, "本书已经下载，已经更新其内容", Toast.LENGTH_SHORT).show()
                            }
                        }
                        val tempDbArray = bookDataDao.loadAllBookData() as ArrayList
                        Log.i("LocalBookUtil:", "book $bookData downloaded")
                        Log.i("LocalBookUtil", "book db now: $tempDbArray")
                    }
                }else{
                    Log.w("LocalBookUtil:", "Empty response body! Check net work???")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("downloadBookUtil:", "onFailure")
                t.printStackTrace()
                val builder = AlertDialog.Builder(MyApplication.context)
                builder.setTitle("阅读器被玩坏了！\n(网络连接超时了)")
                builder.setMessage("这肯定不是阅读器的问题！\n绝对不是！（试试离线登录？）")
                builder.setPositiveButton("确定", null)
                builder.show()
            }
        })

    }

    fun loadBookContentAndRead(bookData: BookData, activityForToast: Activity){
        var bookContent = "下载过程中失败了没有重试！请尝试重新下载！"
        if (bookData.content != "null" && bookData.content != "下载过程中失败了没有重试！请尝试重新下载！"){
            val webService = RetrofitServiceCreator.create(WebService::class.java)
            val call: Call<ResponseBody> = webService.downloadBook(bookData.id.toInt())
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        bookContent = responseBody.string()
                        val intent = Intent(activityForToast, ReaderActivity::class.java)
                        intent.putExtra("bookString", bookContent)
                        intent.putExtra("bookId", "${bookData.id}")
                        activityForToast.startActivity(intent)
                    } else {
                        Log.w("LocalBookUtil:", "Empty response body! Check network or backend???")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("LocalBookUtil:", "onFailure")
                    t.printStackTrace()
                    val builder = AlertDialog.Builder(MyApplication.context)
                    builder.setTitle("阅读器被玩坏了！\n(网络连接超时了)")
                    builder.setMessage("这肯定不是阅读器的问题！\n绝对不是！（试试离线登录？）")
                    builder.setPositiveButton("确定", null)
                    builder.show()
                }
            })
        } else {
            val intent = Intent(activityForToast, ReaderActivity::class.java)
            intent.putExtra("bookString", bookData.content)
            intent.putExtra("bookId", "${bookData.id}")
            activityForToast.startActivity(intent)
        }
    }

    fun deleteBook(bookData: BookData, downLoadFragment: BaseFragment){
        thread {
            bookDataDao.deleteBookData(bookData)
            downLoadFragment.refreshData()
        }
    }
}