package com.example.bookreader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0012J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\u0006\u0010\u0018\u001a\u00020\u0012J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\u0012\u0010\u001a\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\u001a\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0018\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\nH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/example/bookreader/MainActivity;", "Lcom/example/bookreader/BaseActivity;", "()V", "btmNavBar", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "getBtmNavBar", "()Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "exitTime", "", "previousFragmentId", "", "searchFab", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "getSearchFab", "()Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "userData", "Lcom/example/bookreader/data_class/UserData;", "exitApp", "", "getUserData", "intent", "Landroid/content/Intent;", "hideFab", "init", "moveFAB", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "", "keyCode", "event", "Landroid/view/KeyEvent;", "replaceFragment", "fragment", "Lcom/example/bookreader/main_fragments/BaseFragment;", "itemId", "app_debug"})
public final class MainActivity extends com.example.bookreader.BaseActivity {
    private int previousFragmentId = -1;
    private com.example.bookreader.data_class.UserData userData;
    private long exitTime = 100L;
    
    public MainActivity() {
        super();
    }
    
    private final com.google.android.material.bottomnavigation.BottomNavigationView getBtmNavBar() {
        return null;
    }
    
    private final com.google.android.material.floatingactionbutton.FloatingActionButton getSearchFab() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void replaceFragment(com.example.bookreader.main_fragments.BaseFragment fragment, int itemId) {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    public boolean onKeyDown(int keyCode, @org.jetbrains.annotations.Nullable()
    android.view.KeyEvent event) {
        return false;
    }
    
    private final void exitApp() {
    }
    
    private final void getUserData(android.content.Intent intent) {
    }
    
    public final void moveFAB() {
    }
    
    public final void hideFab() {
    }
}