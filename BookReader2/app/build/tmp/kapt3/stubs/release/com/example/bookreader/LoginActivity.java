package com.example.bookreader;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020(H\u0002J\b\u0010*\u001a\u00020(H\u0002J\u0012\u0010+\u001a\u00020(2\b\u0010,\u001a\u0004\u0018\u00010-H\u0014J\b\u0010.\u001a\u00020(H\u0002J\b\u0010/\u001a\u00020(H\u0002J\u0010\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020$H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\nR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\nR\u0014\u0010\u001f\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\nR\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020\u00148BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u0016\u00a8\u00062"}, d2 = {"Lcom/example/bookreader/LoginActivity;", "Lcom/example/bookreader/BaseActivity;", "()V", "autoLoginCheckBox", "Landroid/widget/CheckBox;", "getAutoLoginCheckBox", "()Landroid/widget/CheckBox;", "autoLoginTextView", "Landroid/widget/TextView;", "getAutoLoginTextView", "()Landroid/widget/TextView;", "loginButton", "Landroid/widget/Button;", "getLoginButton", "()Landroid/widget/Button;", "offlineLoginTextView", "getOfflineLoginTextView", "password", "", "passwordEditText", "Landroid/widget/EditText;", "getPasswordEditText", "()Landroid/widget/EditText;", "passwordFile", "Ljava/io/File;", "getPasswordFile", "()Ljava/io/File;", "rememberMeCheckBox", "getRememberMeCheckBox", "rememberMeTextView", "getRememberMeTextView", "signUpButton", "getSignUpButton", "testUserData", "Lcom/example/bookreader/data_class/UserData;", "userId", "", "userNameEditText", "getUserNameEditText", "checkAndRememberPassword", "", "checkIfRememberMe", "init", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLogin", "onSignUp", "showAlert", "alertType", "app_release"})
public final class LoginActivity extends com.example.bookreader.BaseActivity {
    private final com.example.bookreader.data_class.UserData testUserData = null;
    private int userId = 0;
    private java.lang.String password;
    
    public LoginActivity() {
        super();
    }
    
    private final android.widget.CheckBox getRememberMeCheckBox() {
        return null;
    }
    
    private final java.io.File getPasswordFile() {
        return null;
    }
    
    private final android.widget.EditText getUserNameEditText() {
        return null;
    }
    
    private final android.widget.EditText getPasswordEditText() {
        return null;
    }
    
    private final android.widget.TextView getSignUpButton() {
        return null;
    }
    
    private final android.widget.Button getLoginButton() {
        return null;
    }
    
    private final android.widget.TextView getRememberMeTextView() {
        return null;
    }
    
    private final android.widget.TextView getAutoLoginTextView() {
        return null;
    }
    
    private final android.widget.TextView getOfflineLoginTextView() {
        return null;
    }
    
    private final android.widget.CheckBox getAutoLoginCheckBox() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void init() {
    }
    
    private final void checkIfRememberMe() {
    }
    
    private final void onLogin() {
    }
    
    private final void showAlert(int alertType) {
    }
    
    private final void onSignUp() {
    }
    
    private final void checkAndRememberPassword() {
    }
}