package com.example.bookreader

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.bookreader.data_class.UserData
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class ChangePasswordActivity : BaseActivity() {
    private val userData: UserData
        get() {
            return intent.getSerializableExtra("userData") as UserData
        }
    private val submitChangePasswordButton: Button
        get() {
            val submitChangePasswordButton = findViewById<Button>(R.id.submitChangePasswordButton)
            return submitChangePasswordButton
        }
    private val changePasswordOriginalPassword: EditText
        get() {
            val changePasswordOriginalPassword = findViewById<EditText>(R.id.changePasswordOriginalPassword)
            return changePasswordOriginalPassword
        }
    private val changePasswordNewPassword: EditText
        get() {
            val changePasswordNewPassword = findViewById<EditText>(R.id.changePasswordNewPassword)
            return changePasswordNewPassword
        }
    private val changePasswordNewPassword2: EditText
        get() {
            val changePasswordNewPassword2 = findViewById<EditText>(R.id.changePasswordNewPassword2)
            return changePasswordNewPassword2
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        supportActionBar?.hide()
        submitChangePasswordButton.setOnClickListener{onSubmitSignupButtonClick()}
    }

    private fun onSubmitSignupButtonClick(){
        if(changePasswordNewPassword.text.toString() == changePasswordNewPassword2.text.toString()){
            val webService = RetrofitServiceCreator.create(WebService::class.java)
            val call2: Call<ResponseBody> = webService.changePassword(userData.id.toInt(),
                changePasswordOriginalPassword.text.toString(),
                changePasswordNewPassword.text.toString()
            )
            call2.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val responseBodyString = response.body()?.string()

                    Log.i("ChangePasswordActivity:", "onSuccess")
                    Log.i("ChangePasswordActivity: ", "response: ${responseBodyString}")

                    if(responseBodyString == "success"){
                        val builder = AlertDialog.Builder(this@ChangePasswordActivity)
                        builder.setTitle("?????????????????????")
                        builder.setMessage("????????????")
                        builder.setPositiveButton("??????", object: DialogInterface.OnClickListener{
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                finish()
                            }

                        })
                        builder.show()
                    }
                    if (responseBodyString == "failed"){
                        val builder = AlertDialog.Builder(this@ChangePasswordActivity)
                        builder.setTitle("??????????????????")
                        builder.setMessage("?????????~")
                        builder.setPositiveButton("????????????", null)
                        builder.show()
                        changePasswordOriginalPassword.setText("")
                        Log.i("LoginActivity:", "onFailure")
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    //???????????????
                    val builder = AlertDialog.Builder(this@ChangePasswordActivity)
                    builder.setTitle("????????????????????????\n(?????????????????????)")
                    builder.setMessage("????????????????????????????????????\n??????????????????????????????????????????")
                    builder.setPositiveButton("??????", null)
                    builder.show()
                    Log.i("LoginActivity:", "onFailure")
                    t.printStackTrace()
                }
            })
        }else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("?????????")
            builder.setMessage("??????????????????????????????")
            builder.setPositiveButton("???????????????", null)
            builder.show()
            changePasswordNewPassword.setText("")
            changePasswordNewPassword2.setText("")
        }
    }
}