package com.example.bookreader.recyclerview_adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001 BA\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\fH\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001c\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\n\u0010\u0018\u001a\u00060\u0002R\u00020\u0000H\u0002J\u001c\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\fH\u0016J\u001c\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\n\u0010\u0018\u001a\u00060\u0002R\u00020\u0000H\u0002J\u001c\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\n\u0010\u0018\u001a\u00060\u0002R\u00020\u0000H\u0002J\u001c\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00052\n\u0010\u0018\u001a\u00060\u0002R\u00020\u0000H\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/bookreader/recyclerview_adapters/BookRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/bookreader/recyclerview_adapters/BookRecyclerViewAdapter$ViewHolder;", "bookDataList", "Ljava/util/ArrayList;", "Lcom/example/bookreader/data_class/BookData;", "Lkotlin/collections/ArrayList;", "userData", "Lcom/example/bookreader/data_class/UserData;", "mainActivity", "Landroid/app/Activity;", "mode", "", "fragment", "Landroidx/fragment/app/Fragment;", "(Ljava/util/ArrayList;Lcom/example/bookreader/data_class/UserData;Landroid/app/Activity;ILandroidx/fragment/app/Fragment;)V", "getItemCount", "onBindViewHolder", "", "holder", "position", "onBookClick", "bookData", "onCollectionMenuClick", "viewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onDownloadMenuClick", "onHomeMenuClick", "onSearchMenuClick", "ViewHolder", "app_debug"})
public final class BookRecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder> {
    private java.util.ArrayList<com.example.bookreader.data_class.BookData> bookDataList;
    private final com.example.bookreader.data_class.UserData userData = null;
    private final android.app.Activity mainActivity = null;
    private final int mode = 0;
    private final androidx.fragment.app.Fragment fragment = null;
    
    public BookRecyclerViewAdapter(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.bookreader.data_class.BookData> bookDataList, @org.jetbrains.annotations.NotNull()
    com.example.bookreader.data_class.UserData userData, @org.jetbrains.annotations.Nullable()
    android.app.Activity mainActivity, int mode, @org.jetbrains.annotations.Nullable()
    androidx.fragment.app.Fragment fragment) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    private final void onBookClick(com.example.bookreader.data_class.BookData bookData, com.example.bookreader.data_class.UserData userData) {
    }
    
    private final void onHomeMenuClick(com.example.bookreader.data_class.BookData bookData, com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder viewHolder) {
    }
    
    private final void onCollectionMenuClick(com.example.bookreader.data_class.BookData bookData, com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder viewHolder) {
    }
    
    private final void onDownloadMenuClick(com.example.bookreader.data_class.BookData bookData, com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder viewHolder) {
    }
    
    private final void onSearchMenuClick(com.example.bookreader.data_class.BookData bookData, com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter.ViewHolder viewHolder) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/example/bookreader/recyclerview_adapters/BookRecyclerViewAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/example/bookreader/recyclerview_adapters/BookRecyclerViewAdapter;Landroid/view/View;)V", "bookAuthorTextView", "Landroid/widget/TextView;", "getBookAuthorTextView", "()Landroid/widget/TextView;", "bookImageView", "Landroid/widget/ImageView;", "getBookImageView", "()Landroid/widget/ImageView;", "bookNameTextView", "getBookNameTextView", "collectedMark", "getCollectedMark", "lastReadTimeImage", "getLastReadTimeImage", "lastReadTimeText", "getLastReadTimeText", "menuButton", "Landroid/widget/ImageButton;", "getMenuButton", "()Landroid/widget/ImageButton;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView bookImageView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView bookNameTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView bookAuthorTextView = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton menuButton = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView collectedMark = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView lastReadTimeImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView lastReadTimeText = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getBookImageView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBookNameTextView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBookAuthorTextView() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getMenuButton() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getCollectedMark() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getLastReadTimeImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLastReadTimeText() {
            return null;
        }
    }
}