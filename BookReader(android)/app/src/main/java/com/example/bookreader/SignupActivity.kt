package com.example.bookreader

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import com.google.android.material.snackbar.Snackbar
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : BaseActivity() {
    private val submitSignupButton: Button
        get() {
            val submitSignUpButton = findViewById<Button>(R.id.submitSignUpButton)
            return submitSignUpButton
        }
    private val signupUserId: EditText
        get() {val signupUserId = findViewById<EditText>(R.id.signupUsername)
            return signupUserId
        }
    private val signupPassword: EditText
        get() {
            val signupPassword = findViewById<EditText>(R.id.signupPassword)
            return signupPassword
        }
    private val signupPassword2: EditText
        get() {val signupPassword2 = findViewById<EditText>(R.id.signupPassword2)
            return signupPassword2
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        submitSignupButton.setOnClickListener{onSubmitSignupButtonClick()}
    }

    private fun onSubmitSignupButtonClick(){
        if(signupPassword.text.toString() != "" && signupPassword2.text.toString() != "" && signupUserId.text.toString() != ""){
            if (signupPassword.text.toString() == signupPassword2.text.toString()) {
                val webService = RetrofitServiceCreator.create(WebService::class.java)
                val call2: Call<ResponseBody> = webService.signupByIdUrl(signupUserId.text.toString().toInt(), signupPassword2.text.toString())
                call2.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        val responseBody = response.body()?.string()
                        Log.i("signUpActivity:", "onResponse")
                        Log.i("signUpActivity:", "body: $responseBody")
                        if (responseBody == "success"){
                            val builder = AlertDialog.Builder(this@SignupActivity)
                            builder.setTitle("?????????????????????")
                            builder.setMessage("????????????")
                            builder.setPositiveButton("??????", object: DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    val intent = Intent(MyApplication.context, LoginActivity::class.java)
                                    startActivity(intent)
                                }
                            })
                            builder.show()
                            Log.i("signupActivity:", "signup success: ${signupPassword.text}, ${signupPassword2.text}")
                        }else{
                            val builder = AlertDialog.Builder(this@SignupActivity)
                            builder.setTitle("????????????????????????????????????????????????")
                            builder.setMessage("????????????????????????")
                            builder.setPositiveButton("????????????", null)
                            builder.setOnDismissListener {
                                Snackbar.make(findViewById<ConstraintLayout>(R.id.signupSnackBarAnchor), R.string.???????????????id, Snackbar.LENGTH_SHORT)
                                    .setAction(R.string.???????????????) {
                                        signupUserId.setText("")
                                    }
                                    .show()
                            }
                            builder.show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.i("signUpActivity:", "onFailure")
                        t.printStackTrace()
                        val builder = AlertDialog.Builder(this@SignupActivity)
                        builder.setTitle("????????????????????????\n(?????????????????????)")
                        builder.setMessage("????????????????????????????????????\n????????????????????????????????????????????????")
                        builder.setPositiveButton("??????", null)
                        builder.show()
                    }
                })
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("?????????")
                builder.setMessage("??????????????????????????????")
                builder.setPositiveButton("???????????????", null)
                builder.setOnDismissListener {
                    Snackbar.make(findViewById<ConstraintLayout>(R.id.signupSnackBarAnchor), R.string.?????????????????????, Snackbar.LENGTH_SHORT)
                        .setAction(R.string.??????) {
                            signupPassword.setText("")
                            signupPassword2.setText("")
                        }
                        .show()
                }
                builder.show()
            }
        }
    }
}