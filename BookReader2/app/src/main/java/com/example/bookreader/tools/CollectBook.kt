package com.example.bookreader.tools

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bookreader.MyApplication
import com.example.bookreader.data_class.BookData
import com.example.bookreader.data_class.UserData
import com.example.bookreader.main_fragments.BaseFragment
import com.example.bookreader.main_fragments.CollectionFragment
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CollectBook {
    fun collectBook(userData: UserData, bookId: Long){
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<ResponseBody> = webService.collectBook(userData.id.toInt(), bookId.toInt())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>) {
                Log.i("collectBook.class:", "${response.body()?.string()}")
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("collectBook.class:", "collect failed! onFailure!")
            }
        })
    }

    fun collectBook(userData: UserData, bookId: Long, fragment: BaseFragment){
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<ResponseBody> = webService.collectBook(userData.id.toInt(), bookId.toInt())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>) {
                Log.i("collectBook.class:", "${response.body()?.string()}")
                fragment.refreshData()
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("collectBook.class:", "collect failed! onFailure!")
                fragment.makeAlert()
            }
        })
    }

    fun unCollectBook(userData: UserData, bookId: Long, fragment: BaseFragment){
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<ResponseBody> = webService.unCollectBook(userData.id.toInt(), bookId.toInt())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>) {
                Log.i("collectBook.class:", "collected ${response.body()?.string()}")
                fragment.refreshData()
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("collectBook.class:", "collect failed! onFailure!")
                fragment.makeAlert()
            }
        })
    }

    fun unCollectBook(userData: UserData, bookId: Long){
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<ResponseBody> = webService.unCollectBook(userData.id.toInt(), bookId.toInt())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>) {
                Log.i("collectBook.class:", "uncollected ${response.body()?.string()}")
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("collectBook.class:", "collect failed! onFailure!")
            }
        })
    }

}