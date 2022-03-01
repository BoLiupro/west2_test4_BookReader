package com.example.bookreader.web_requests;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J,\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\r\u001a\u00020\t2\b\b\u0001\u0010\u000e\u001a\u00020\tH\'J\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0010\u001a\u00020\u0007H\'J(\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0007H\'J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J\u001e\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J\u001e\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J\u001e\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0007H\'J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\'J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\u000e\u001a\u00020\tH\'J\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0010\u001a\u00020\u0007H\'J\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\u000e\u001a\u00020\tH\'J\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\u0010\u001a\u00020\u0007H\'\u00a8\u0006\u001d"}, d2 = {"Lcom/example/bookreader/web_requests/WebService;", "", "allSearch", "Lretrofit2/Call;", "", "Lcom/example/bookreader/data_class/BookData;", "userId", "", "searchContent", "", "changePassword", "Lokhttp3/ResponseBody;", "id", "originalPassword", "password", "collectBook", "bookId", "collectionSearch", "downloadBook", "getBook", "getBookData", "getBookRecommend", "getCollectedBook", "getHistory", "getSuggestion", "loginByIdUrl", "setHistory", "signupByIdUrl", "unCollectBook", "app_release"})
public abstract interface WebService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "index/login")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<okhttp3.ResponseBody> loginByIdUrl(@retrofit2.http.Field(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "password")
    java.lang.String password);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "index/register")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<okhttp3.ResponseBody> signupByIdUrl(@retrofit2.http.Field(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "password")
    java.lang.String password);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "fiction/recommend")
    public abstract retrofit2.Call<okhttp3.ResponseBody> getSuggestion();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "fiction/keyword")
    public abstract retrofit2.Call<okhttp3.ResponseBody> getBook(@retrofit2.http.Query(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "fiction/intro")
    public abstract retrofit2.Call<com.example.bookreader.data_class.BookData> getBookData(@retrofit2.http.Query(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "fiction/recommend")
    public abstract retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> getBookRecommend(@retrofit2.http.Query(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "getTheHistory")
    public abstract retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> getHistory(@retrofit2.http.Query(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "getCollection")
    public abstract retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> getCollectedBook(@retrofit2.http.Query(value = "id")
    int id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "setCollection")
    public abstract retrofit2.Call<okhttp3.ResponseBody> collectBook(@retrofit2.http.Query(value = "id")
    int userId, @retrofit2.http.Query(value = "fic_id")
    int bookId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "setTheHistory")
    public abstract retrofit2.Call<okhttp3.ResponseBody> setHistory(@retrofit2.http.Query(value = "id")
    int userId, @retrofit2.http.Query(value = "fic_id")
    int bookId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "deleteCollection")
    public abstract retrofit2.Call<okhttp3.ResponseBody> unCollectBook(@retrofit2.http.Query(value = "id")
    int userId, @retrofit2.http.Query(value = "fic_id")
    int bookId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "fiction/text")
    public abstract retrofit2.Call<okhttp3.ResponseBody> downloadBook(@retrofit2.http.Query(value = "fic_id")
    int bookId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "index/changePassword")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<okhttp3.ResponseBody> changePassword(@retrofit2.http.Query(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "password")
    java.lang.String originalPassword, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "new_password")
    java.lang.String password);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "fiction/search")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> allSearch(@retrofit2.http.Field(value = "id")
    int userId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "search")
    java.lang.String searchContent);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "fiction/searchInCollection")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> collectionSearch(@retrofit2.http.Field(value = "id")
    int userId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "search")
    java.lang.String searchContent);
}