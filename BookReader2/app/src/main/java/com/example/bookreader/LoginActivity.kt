package com.example.bookreader

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.bifan.txtreaderlib.ui.HwTxtPlayActivity
import com.example.bookreader.MyApplication.Companion.context
import com.example.bookreader.data_class.UserData
import com.example.bookreader.tools.FileToJson
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import com.google.gson.JsonObject
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import kotlin.concurrent.thread


class LoginActivity : BaseActivity() {
    private val rememberMeCheckBox: CheckBox
        get() {
            val rememberMeCheckBox = findViewById<CheckBox>(R.id.rememberMeCheckBox)
            return rememberMeCheckBox
        }
    private val passwordFile: File
        get() {
            return File("$filesDir/password_temp/password_temp.json")
        }
    private val userNameEditText: EditText
        get() {
            val userNameEditText = findViewById<EditText>(R.id.loginUsername)
            return userNameEditText
        }
    private val passwordEditText: EditText
        get() {
            val passwordEditText = findViewById<EditText>(R.id.loginPassword)
            return passwordEditText
        }
    private val signUpButton: TextView
        get() {
            val signUpButton = findViewById<TextView>(R.id.signUpButton)
            return signUpButton
        }
    private val loginButton: Button
        get() {
            val loginButton = findViewById<Button>(R.id.loginButton)
            return loginButton
        }
    private val rememberMeTextView: TextView
        get() {
            val rememberMeTextView = findViewById<TextView>(R.id.rememberMeTextView)
            return rememberMeTextView
        }
    private val autoLoginTextView: TextView
        get() {
            val autoLoginTextView = findViewById<TextView>(R.id.autoLoginTextView)
            return autoLoginTextView
        }
    private val offlineLoginTextView: TextView
        get() {
            val offlineLoginTextView = findViewById<TextView>(R.id.offlineLoginTextView)
            return offlineLoginTextView
        }
    private val autoLoginCheckBox: CheckBox
        get() {
            val autoLoginCheckBox = findViewById<CheckBox>(R.id.autoLoginCheckBox)
            return autoLoginCheckBox
        }
    private val testUserData = UserData(-1)
    //下面两个变量与填写内容一致：
    private var userId = 0
    private var password = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }
    
    /*一个用来运行测试代码的按钮，无视：
    private fun testAnything(){
        fun okhttpTest(){
            var response: okhttp3.Response? = null
            thread{
                try{
                    val client = OkHttpClient()
                    val requestBody = FormBody.Builder()
                        .add("id", "1")
                        .add("password", "111")
                        .build()
                    val request = Request.Builder()
                        .url("http://a30163f799.51vip.biz/index/login")
                        .post(requestBody)
                        .build()
                    response = client.newCall(request).execute()
                }catch(e: Exception){
                    Log.i("testAnything:", "exception, under this condition, it's usually because server closed..")
                }
            }
            Log.i("testAnything:", "response: ${response?.body}")
        }
        fun retrofitTest(){
            val webService = RetrofitServiceCreator.create(WebService::class.java)
            val call2: Call<ResponseBody> = webService.loginByIdUrl(1, "111")
            call2.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.i("testAnything:", "onResponse")
                    Log.i("testAnything:", "body: "+ response.body()?.string())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("testAnything:", "onFailure")
                    t.printStackTrace()
                }
            })
        }
        fun bookReaderTest(){
            HwTxtPlayActivity
                .loadTxtFile(this, "$filesDir/安达与岛村9.txt")
        }
        fun bookReaderActivityTest(){
            val intent = Intent(this, ReaderActivity::class.java)
            val file = File("$filesDir/安达与岛村9.txt")
            val bookString = file.readText()
            intent.putExtra("bookString", bookString)
            intent.putExtra("bookId", "9999")
            startActivity(intent)
        }

        //just for button and running:
        val button = findViewById<Button>(R.id.testAnythingButton)
        button.setOnClickListener{
            bookReaderActivityTest()
        }
    }*/

    private fun init(){
        //Views:
        supportActionBar?.hide()

        //字体：
        val ttf = Typeface.createFromAsset(assets, "fonts/microsoft_yahei.ttf")
        loginButton.typeface = ttf
        signUpButton.typeface = ttf
        rememberMeTextView.typeface = ttf
        userNameEditText.typeface = ttf
        passwordEditText.typeface = ttf
        offlineLoginTextView.typeface = ttf

        checkIfRememberMe()

        //设置点击事件
            //这个小函数处理一下两个checkbox的UI逻辑：
            fun onRememberMeCheckBoxStatusChange(){
                if(rememberMeCheckBox.isChecked){
                    autoLoginCheckBox.isClickable = true
                    autoLoginCheckBox.alpha = 1f
                    autoLoginTextView.alpha = 1f
                }
                if(!rememberMeCheckBox.isChecked){
                    autoLoginCheckBox.isClickable = false
                    autoLoginCheckBox.isChecked = false
                    autoLoginCheckBox.alpha = 0.5f
                    autoLoginTextView.alpha = 0.5f
                }
            }
        onRememberMeCheckBoxStatusChange()
        signUpButton.setOnClickListener{onSignUp()}
        loginButton.setOnClickListener{onLogin()}
        rememberMeTextView.setOnClickListener{
            rememberMeCheckBox.isChecked = !rememberMeCheckBox.isChecked
            if(rememberMeCheckBox.isChecked){
                autoLoginCheckBox.isClickable = true
                autoLoginCheckBox.alpha = 1f
                autoLoginTextView.alpha = 1f
            }
            if(!rememberMeCheckBox.isChecked){
                autoLoginCheckBox.isClickable = false
                autoLoginCheckBox.isChecked = false
                autoLoginCheckBox.alpha = 0.5f
                autoLoginTextView.alpha = 0.5f
            }
        }
        autoLoginTextView.setOnClickListener{
            if(autoLoginCheckBox.isClickable){autoLoginCheckBox.isChecked = !autoLoginCheckBox.isChecked}
            onRememberMeCheckBoxStatusChange()
        }
        offlineLoginTextView.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("userData", UserData(0, 0))
            intent.putExtra("isOffline", true)
            ActivityCollector.onlyStart(intent, this)
        }
        rememberMeCheckBox.setOnClickListener{
            onRememberMeCheckBoxStatusChange()
        }
    }

    private fun checkIfRememberMe(){
        if(passwordFile.isFile)
        {//有文件：设置密码账号为默认值
            rememberMeCheckBox.isChecked = true
            val jsonObject = FileToJson.fileToJson(passwordFile)
            userId = jsonObject?.get("UserName").toString().replace("\"","").toInt()
            password = jsonObject?.get("Password").toString().replace("\"","")
            val isAutoLogin = jsonObject?.get("isAutoLogin").toString().replace("\"","").toBoolean()
            Log.i("Auto Login:", "$isAutoLogin")
            userNameEditText.setText(userId.toString())
            passwordEditText.setText(password)
            if(intent.getStringExtra("userId") != null){
                userNameEditText.setText(intent.getStringExtra("userId"))
                passwordEditText.setText(intent.getStringExtra("userPassword"))
            }
            if(isAutoLogin){
                autoLoginCheckBox.isChecked = true
                onLogin()
            }
        }
    }

    private fun onLogin(){
        //输入判空，id不判是否数字是因为在xml做了限制：
        if(userNameEditText.text.toString() != ""
            && userNameEditText.text.toString() != "") {

            userId = userNameEditText.text.toString().toInt()
            password = passwordEditText.text.toString()

            //不联网测试用后门：
            if (userId == 114514) {
                //不联网测试用后门：
                checkAndRememberPassword()
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("userData", testUserData)
                startActivity(intent)
            }else {
                //密码发送，返回用户识别码：
                val webService = RetrofitServiceCreator.create(WebService::class.java)
                val call2: Call<ResponseBody> = webService.loginByIdUrl(userId, password)
                call2.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        Log.i("loginActivity:", "onResponse")
                        val userCode = response.body()?.string()
                        Log.i(
                            "loginActivity:",
                            "responseBody: " + response.body()?.string() + "num: $userCode"
                        )
                        //进行判断和登录操作：
                        if (userCode != "failed") {
                            //记住密码？
                            checkAndRememberPassword()
                            //启动MainActivity
                            val intent = Intent(MyApplication.context, MainActivity::class.java)
                            intent.putExtra("userData",
                                userCode?.let { UserData(userId.toLong(), it.toInt()) }
                            )
                            startActivity(intent)
                        } else {
                            passwordEditText.setText("")
                            showAlert(0)
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        //网络出错：
                        showAlert(1)
                        Log.i("LoginActivity:", "onFailure")
                        t.printStackTrace()
                    }
                })
            }
        }
    }

    private fun showAlert(alertType: Int){
        val WRONG_PASSWORD = 0
        val NETWORK_FATAL = 1
        val builder = AlertDialog.Builder(this)
        if(alertType == WRONG_PASSWORD){//密码错误
            builder.setTitle("用户名或密码错误")
            builder.setMessage("请重试~")
            builder.setPositiveButton("再输入一次", null)
        }
        if (alertType == NETWORK_FATAL){
            builder.setTitle("阅读器被玩坏了！\n(网络连接超时了)")
            builder.setMessage("这肯定不是阅读器的问题！\n绝对不是！（试试离线登录？）")
            builder.setPositiveButton("确定", null)
        }
        builder.show()
    }

    private fun onSignUp(){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun checkAndRememberPassword(){
        if(rememberMeCheckBox.isChecked){
            val jsonObject = JsonObject()
            jsonObject.addProperty("UserName", userId.toString())
            jsonObject.addProperty("Password", password)
            jsonObject.addProperty("isAutoLogin", autoLoginCheckBox.isChecked.toString())

            //新建文件夹
            val fileDir = File("$filesDir/password_temp")
            if(!fileDir.isDirectory){
                fileDir.mkdir()
            }

            //重新写入文件
            if(passwordFile.isFile){
                passwordFile.delete()
            }
            val fOutStream = FileOutputStream(passwordFile)
            val fOutStreamWriter = OutputStreamWriter(fOutStream)
            fOutStreamWriter.write(jsonObject.toString())
            fOutStreamWriter.close(); fOutStream.close()
        }else{
            passwordFile.delete()
        }
    }
}
