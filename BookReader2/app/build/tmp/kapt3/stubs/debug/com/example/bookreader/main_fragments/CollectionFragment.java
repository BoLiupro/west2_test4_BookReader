package com.example.bookreader.main_fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\u000e\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u0007J&\u0010#\u001a\u0004\u0018\u00010\n2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\n2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010,\u001a\u00020\u001fH\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0006j\b\u0012\u0004\u0012\u00020\u000e`\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/example/bookreader/main_fragments/CollectionFragment;", "Lcom/example/bookreader/main_fragments/BaseFragment;", "userData", "Lcom/example/bookreader/data_class/UserData;", "(Lcom/example/bookreader/data_class/UserData;)V", "collectionArray", "Ljava/util/ArrayList;", "Lcom/example/bookreader/data_class/BookData;", "Lkotlin/collections/ArrayList;", "collectionConstraintLayout", "Landroid/view/View;", "getCollectionConstraintLayout", "()Landroid/view/View;", "jsonArray", "Lcom/google/gson/JsonObject;", "mainActivity", "Lcom/example/bookreader/MainActivity;", "offlineNotice", "Landroid/widget/ImageView;", "getOfflineNotice", "()Landroid/widget/ImageView;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "recyclerViewAdapter", "Lcom/example/bookreader/recyclerview_adapters/BookRecyclerViewAdapter;", "swipeRefresh", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "testCollectionArray", "getCollectionArray", "", "initTestData", "makeSnackbar", "bookDataData", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "refreshData", "app_debug"})
public final class CollectionFragment extends com.example.bookreader.main_fragments.BaseFragment {
    private final com.example.bookreader.data_class.UserData userData = null;
    private java.util.ArrayList<com.example.bookreader.data_class.BookData> collectionArray;
    private java.util.ArrayList<com.example.bookreader.data_class.BookData> testCollectionArray;
    private final java.util.ArrayList<com.google.gson.JsonObject> jsonArray = null;
    private com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter recyclerViewAdapter;
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout swipeRefresh;
    private com.example.bookreader.MainActivity mainActivity;
    
    public CollectionFragment(@org.jetbrains.annotations.NotNull()
    com.example.bookreader.data_class.UserData userData) {
        super();
    }
    
    private final android.view.View getCollectionConstraintLayout() {
        return null;
    }
    
    private final android.widget.ImageView getOfflineNotice() {
        return null;
    }
    
    private final androidx.recyclerview.widget.RecyclerView getRecyclerView() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void refreshData() {
    }
    
    private final void initTestData() {
    }
    
    private final void getCollectionArray(com.example.bookreader.data_class.UserData userData) {
    }
    
    public final void makeSnackbar(@org.jetbrains.annotations.NotNull()
    com.example.bookreader.data_class.BookData bookDataData) {
    }
}