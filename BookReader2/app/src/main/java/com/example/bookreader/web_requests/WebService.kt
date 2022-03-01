package com.example.bookreader.web_requests

import com.example.bookreader.data_class.BookData
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface WebService {
    /*
    @GET("v7/weather/now")
    fun getData(@Query("key") key: String,
                @Query("location") location: String): Call<WeatherWebData>

    @POST("index/login")
    fun loginByIdJson(@Body loginJsonRequest: LoginJsonRequest): Call<ResponseBody>

    @POST("index/register")
    fun signupByJson(@Body signupJsonRequest: SignupJsonRequest): Call<ResponseBody>
    */

    @FormUrlEncoded
    @POST("index/login")
    fun loginByIdUrl(@Field("id") id: Int, @Field("password") password: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("index/register")
    fun signupByIdUrl(@Field("id") id: Int, @Field("password") password: String): Call<ResponseBody>

    @GET("fiction/recommend")
    fun getSuggestion(): Call<ResponseBody>

    @GET("fiction/keyword")
    fun getBook(@Query("id") id: Int): Call<ResponseBody>

    @GET("fiction/intro")
    fun getBookData(@Query("id") id: Int): Call<BookData>

    @GET("fiction/recommend")
    fun getBookRecommend(@Query("id") id: Int): Call<List<BookData>>

    @GET("getTheHistory")
    fun getHistory(@Query("id") id: Int): Call<List<BookData>>

    @GET("getCollection")
    fun getCollectedBook(@Query("id") id: Int): Call<List<BookData>>

    @GET("setCollection")
    fun collectBook(@Query("id") userId: Int, @Query("fic_id") bookId: Int): Call<ResponseBody>

    @GET("setTheHistory")
    fun setHistory(@Query("id") userId: Int, @Query("fic_id") bookId: Int): Call<ResponseBody>

    @GET("deleteCollection")
    fun unCollectBook(@Query("id") userId: Int, @Query("fic_id") bookId: Int): Call<ResponseBody>

    @GET("fiction/text")
    fun downloadBook(@Query("fic_id") bookId: Int): Call<ResponseBody>

    @FormUrlEncoded
    @POST("index/changePassword")
    fun changePassword(@Query("id") id: Int,@Field("password") originalPassword: String,
                       @Field("new_password") password: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("fiction/search")
    fun allSearch(@Field("id") userId: Int, @Field("search") searchContent: String): Call<List<BookData>>

    @FormUrlEncoded
    @POST("fiction/searchInCollection")
    fun collectionSearch(@Field("id") userId: Int, @Field("search") searchContent: String): Call<List<BookData>>
}