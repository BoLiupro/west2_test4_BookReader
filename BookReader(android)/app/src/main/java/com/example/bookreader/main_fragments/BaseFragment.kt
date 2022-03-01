package com.example.bookreader.main_fragments

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import java.lang.RuntimeException

open class BaseFragment: Fragment() {
    open fun refreshData() {
        Log.e("refresh fragment data:", "refresh hasn't been implemented!")
        throw RuntimeException("method refresh() hasn't been implemented!")
    }

    fun makeAlert() {
        val builder = AlertDialog.Builder(activity as Activity)
        builder.setTitle("阅读器被玩坏了！\n(网络连接超时了)")
        builder.setMessage("这肯定不是阅读器的问题！\n绝对不是！（试试离线登录？）")
        builder.setPositiveButton("确定", null)
        builder.show()
    }
}