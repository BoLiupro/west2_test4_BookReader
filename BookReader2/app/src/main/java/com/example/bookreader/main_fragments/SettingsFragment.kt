package com.example.bookreader.main_fragments

import android.content.Intent
import android.os.Bundle
import android.os.UserHandle
import android.service.autofill.UserData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.bookreader.*
import com.example.bookreader.ActivityCollector.finishAll
import com.example.bookreader.ActivityCollector.onlyStart
import java.io.File
import java.io.Serializable

class SettingsFragment(val mainActivity: MainActivity, private val userData: com.example.bookreader.data_class.UserData): BaseFragment() {
    private val changePasswordButton: Button?
        get() {
            val changePasswordButton = view?.findViewById<Button>(R.id.changePasswordButton)
            return changePasswordButton
        }
    private val logOutButton: Button?
        get() {
            val logOutButton = view?.findViewById<Button>(R.id.logOutButton)
            return logOutButton
        }
    private val passwordFile: File
        get() {
            val filesDir = MyApplication.context.filesDir.path
            return File("$filesDir/password_temp/password_temp.json")
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(userData.id != 0L){
            changePasswordButton?.setOnClickListener {
                onChangePassword()
            }
            logOutButton?.setOnClickListener {
                onLogout()
            }
        }else{
            changePasswordButton?.visibility = View.GONE
            logOutButton?.visibility = View.GONE
            val offlineNotice = view.findViewById<ImageView>(R.id.settingOfflineNotice)
            offlineNotice.visibility = View.VISIBLE
        }
    }

    private fun onReadingHistory() {
        //val intent = Intent(mainActivity, )
    }

    private fun onLogout() {
        if(passwordFile.isFile){
            passwordFile.delete()
        }
        Log.i("log out", "deleted $passwordFile")
        val intent = Intent(mainActivity, LoginActivity::class.java)
        onlyStart(intent, mainActivity)
    }

    private fun onChangePassword() {
        val intent = Intent(mainActivity, ChangePasswordActivity::class.java)
        intent.putExtra("userData", userData)
        mainActivity.startActivity(intent)
    }

    override fun refreshData() {
    }
}