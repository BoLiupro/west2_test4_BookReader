package com.example.bookreader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\u001fH\u0002J\u0012\u0010\"\u001a\u00020\u001f2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014R(\u0010\u0003\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/example/bookreader/SearchActivity;", "Lcom/example/bookreader/BaseActivity;", "()V", "call", "Lretrofit2/Call;", "", "Lcom/example/bookreader/data_class/BookData;", "getCall", "()Lretrofit2/Call;", "setCall", "(Lretrofit2/Call;)V", "mode", "", "getMode", "()I", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "searchArray", "Ljava/util/ArrayList;", "searchBar", "Landroid/widget/EditText;", "getSearchBar", "()Landroid/widget/EditText;", "searchBg", "Landroid/widget/ImageView;", "getSearchBg", "()Landroid/widget/ImageView;", "testBookDataArray", "userData", "Lcom/example/bookreader/data_class/UserData;", "doSearchAndGetArray", "", "initTestData", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class SearchActivity extends com.example.bookreader.BaseActivity {
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private java.util.ArrayList<com.example.bookreader.data_class.BookData> searchArray;
    private com.example.bookreader.data_class.UserData userData;
    private java.util.ArrayList<com.example.bookreader.data_class.BookData> testBookDataArray;
    @org.jetbrains.annotations.Nullable()
    private retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> call;
    
    public SearchActivity() {
        super();
    }
    
    private final android.widget.EditText getSearchBar() {
        return null;
    }
    
    private final android.widget.ImageView getSearchBg() {
        return null;
    }
    
    private final int getMode() {
        return 0;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initView() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> getCall() {
        return null;
    }
    
    public final void setCall(@org.jetbrains.annotations.Nullable()
    retrofit2.Call<java.util.List<com.example.bookreader.data_class.BookData>> p0) {
    }
    
    private final void doSearchAndGetArray() {
    }
    
    private final void initTestData() {
    }
}