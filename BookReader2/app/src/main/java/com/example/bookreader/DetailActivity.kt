package com.example.bookreader

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewAnimator
import com.bumptech.glide.Glide
import com.example.bookreader.data_class.BookData
import com.example.bookreader.data_class.UserData
import com.example.bookreader.main_fragments.BaseFragment
import com.example.bookreader.tools.CollectBook
import com.example.bookreader.tools.LocalBookUtil
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.properties.Delegates

class DetailActivity : BaseActivity() {
    private val userData: UserData
        get() {
            val userData = intent.getSerializableExtra("userData") as UserData
            return userData
        }
    private lateinit var bookData: BookData
    private var isBookCollected by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //没整明白bookData为什么不能作为全局变量，做了之后get不到对象了?
        bookData = intent.getSerializableExtra("bookData") as BookData
        isBookCollected = bookData.isCollected
        initView()
    }
    //collect button 有BUG
    private fun initView(){
        val introductionTv = findViewById<TextView>(R.id.detailBookIntroduction)
        val detailImage = findViewById<ImageView>(R.id.detailBookImage)
        val authorTv = findViewById<TextView>(R.id.detailBookAuthor)
        val tagTv = findViewById<TextView>(R.id.detailBookTag)
        val collectButton = findViewById<FloatingActionButton>(R.id.detailCollect)
        val readButton = findViewById<FloatingActionButton>(R.id.detailRead)
        val downloadButton = findViewById<FloatingActionButton>(R.id.detailDownload)
        if(bookData.content != null){
            collectButton.visibility = View.INVISIBLE
            downloadButton.visibility = View.INVISIBLE
        }else if (isBookCollected){
            collectButton.setImageResource(R.drawable.ic_collect_fill)
        }
        introductionTv.text = bookData.intro
        authorTv.text = bookData.author
        tagTv.text = "${bookData.rate}"
        Glide.with(MyApplication.context).load(bookData.coverUrl)
            .into(detailImage)
        collectButton.setOnClickListener{
            if(isBookCollected){
                collectButton.setImageResource(R.drawable.ic_collect_empty)
                isBookCollected = false
            }else{
                collectButton.setImageResource(R.drawable.ic_collect_fill)
                isBookCollected = true
            }
        }
        downloadButton.setOnClickListener{
            LocalBookUtil.downloadBook(bookData, this)
        }
        readButton.setOnClickListener{
            LocalBookUtil.loadBookContentAndRead(bookData, this)
            val webService = RetrofitServiceCreator.create(WebService::class.java)
            val call: Call<ResponseBody> = webService.setHistory(userData.id.toInt(), bookData.id.toInt())
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    Log.i("DetailActivity:", "book opened: ${response.body()?.string()}")
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.i("DetailActivity:", "set history failed! onFailure!")
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        if(isBookCollected){
            CollectBook.collectBook(userData, bookData.id)
        }else{
            CollectBook.unCollectBook(userData, bookData.id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(isBookCollected){
            CollectBook.collectBook(userData, bookData.id)
        }else{
            CollectBook.unCollectBook(userData, bookData.id)
        }
    }
}