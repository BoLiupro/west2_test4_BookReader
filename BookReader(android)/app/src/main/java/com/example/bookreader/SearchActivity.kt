package com.example.bookreader

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.data_class.BookData
import com.example.bookreader.data_class.UserData
import com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter
import com.example.bookreader.tools.JsonToBookData
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private var searchArray = ArrayList<BookData>()
    private lateinit var userData: UserData
    private var testBookDataArray = ArrayList<BookData>()
    private val searchBar: EditText
        get() {
            val searchBar = findViewById<EditText>(R.id.searchPlaceEdit)
            return searchBar
        }
    private val searchBg: ImageView
        get() {
            val view = findViewById<ImageView>(R.id.searchBgImageView)
            return view
        }
    private val mode: Int
        get() {
            return intent.getIntExtra("searchMode", -1)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTestData()
        setContentView(R.layout.activity_search)
        userData = intent.getSerializableExtra("userData") as UserData
        initView()
    }

    private fun initView(){
        recyclerView = findViewById(R.id.searchRecyclerView)
        //输入框回车搜索：
        searchBar.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearchAndGetArray()
                return@OnEditorActionListener true
            }
            false
        })
    }
    var call: Call<List<BookData>>? = null

    private fun doSearchAndGetArray(){
        searchArray.clear()
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        Log.i("searchActivity:", "search mode: $mode")
        if (mode == 0){
            call =
                webService.allSearch(userData.id.toInt(), searchBar.text.toString())
        }else if (mode == 1){
            call =
                webService.collectionSearch(userData.id.toInt(), searchBar.text.toString())
        }else{
            Toast.makeText(this, "搜索出错了，bug！", Toast.LENGTH_SHORT).show()
            finish()
        }
        call?.enqueue(object : Callback<List<BookData>> {
            override fun onResponse(
                call: Call<List<BookData>>,
                response: Response<List<BookData>>
            ) {
                val responseBody = response.body()
                Log.i("searchActivity:", "response body: ${responseBody as ArrayList<BookData>}, raw content${responseBody}")
                if (responseBody.toString() != "[]" && responseBody.toString() !=
                    "[BookData(id=0, name=null, author=null, coverUrl=null, intro=null, " +
                    "rate=0.0, isCollected=false, content=null)]"
                )//输入空字符的时候的返回值就是这样的，懒得改了，匹配一下
                    {
                    searchArray.addAll(responseBody)
                    recyclerView.layoutManager = LinearLayoutManager(this@SearchActivity)
                    recyclerView.adapter = BookRecyclerViewAdapter(
                        searchArray, userData, this@SearchActivity, 3, null
                    )
                    searchBg.visibility = View.INVISIBLE

                    for (bookData in searchArray) {
                        Log.i("searchActivity:", "received book data: $bookData")
                    }
                }else{
                    recyclerView.adapter = BookRecyclerViewAdapter(
                        ArrayList(), userData, this@SearchActivity, 3, null
                    )
                    searchBg.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<BookData>>, t: Throwable) {
                Log.i("searchActivity:", "onFailure")
                t.printStackTrace()
                val builder = AlertDialog.Builder(this@SearchActivity)
                builder.setTitle("阅读器被玩坏了！\n(网络连接超时了)")
                builder.setMessage("这肯定不是阅读器的问题！\n绝对不是！（试试离线登录？）")
                builder.setPositiveButton("确定", null)
                builder.show()
            }
        })
    }

    private fun initTestData(){
        //book data:
        val jsonArray = ArrayList<JsonObject>()
        val json = JsonObject()
        json.addProperty("id", "123546")
        json.addProperty("bookName","TEST BOOK!")
        json.addProperty("intro", "INTRO OF TEST BOOK!")
        json.addProperty("rate", 5)
        json.addProperty("author", "MYSELF")
        json.addProperty("coverUrl", "http://a30163f799.51vip.biz/picture/id==1.jpg")
        json.addProperty("tag", "轻小说")
        json.addProperty("isCollected", "false")
        val json2 = JsonObject()
        json2.addProperty("id", "14514")
        json2.addProperty("bookName","TEST BOOK!")
        json2.addProperty("intro", "INTRO OF TEST BOOK!")
        json2.addProperty("rate", 5)
        json2.addProperty("author", "MYSELF")
        json2.addProperty("coverUrl", "https://pic.imgdb.cn/item/62021fc22ab3f51d91f18cd3.jpg")
        json2.addProperty("tag", "轻小说")
        json2.addProperty("isCollected", "true")
        jsonArray.add(json)
        jsonArray.add(json2)
        testBookDataArray.clear()
        testBookDataArray.addAll(JsonToBookData.shortJsonToBookDataList(jsonArray))
        jsonArray.clear()
        Log.i("test data init", testBookDataArray.toString())
    }
}
