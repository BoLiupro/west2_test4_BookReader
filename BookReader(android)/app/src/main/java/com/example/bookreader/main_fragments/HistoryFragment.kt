package com.example.bookreader.main_fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bookreader.MainActivity
import com.example.bookreader.R
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


class HistoryFragment(private val userData: UserData, private val mainActivity: MainActivity): BaseFragment() {
    private var testBookDataArray: ArrayList<BookData> = ArrayList()
    private var bookDataArray: ArrayList<BookData> = ArrayList()
    private val jsonArray: ArrayList<JsonObject> = ArrayList()
    private lateinit var recyclerViewAdapter: BookRecyclerViewAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val offlineNotice: ImageView?
        get() {
            val view = view?.findViewById<ImageView>(R.id.historyOfflineNotice)
            return view
        }
    private val recyclerView: RecyclerView?
        get() {
            val view = view?.findViewById<RecyclerView>(R.id.historyRecyclerView)
            return view
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewAdapter = BookRecyclerViewAdapter(bookDataArray, userData, mainActivity, 3, this)
        swipeRefresh = view.findViewById(R.id.historySwipeRefreshLayout)

        if(userData.id != 0L){
        //获取书籍列表
            getBookArray()
        //初始化recyclerView
            val recyclerView = activity?.findViewById<RecyclerView>(R.id.historyRecyclerView)
            recyclerView?.layoutManager = LinearLayoutManager(activity)
            recyclerView?.adapter = recyclerViewAdapter
        //swipeRefresh:
            swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
            swipeRefresh.setOnRefreshListener {
            refreshData()
                }
        }else{
            offlineNotice?.visibility = View.VISIBLE
            recyclerView?.visibility = View.INVISIBLE
            mainActivity.hideFab()
        }
    }

    override fun refreshData(){
        getBookArray()
    }

    private fun getBookArray(){
        bookDataArray.clear()
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<List<BookData>> = webService.getHistory(userData.id.toInt())
        call.enqueue(object : Callback<List<BookData>> {
            override fun onResponse(
                call: Call<List<BookData>>,
                response: Response<List<BookData>>) {
                val responseBody = response.body()
                if (responseBody != null){
                    val tempArray = responseBody as ArrayList
                    tempArray.reverse()
                    bookDataArray.addAll(tempArray)
                    for (bookData in bookDataArray) {
                        Log.i("historyFragment:", "received book data: $bookData raw content: ${responseBody}")
                    }
                }else{
                    Log.w("historyFragment:", "Empty response body! Check net work???")
                }
                recyclerViewAdapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<BookData>>, t: Throwable) {
                Log.i("historyFragment:", "onFailure")
                t.printStackTrace()
                swipeRefresh.isRefreshing = false
                val builder = AlertDialog.Builder(mainActivity)
                builder.setTitle("阅读器被玩坏了！\n(网络连接超时了)")
                builder.setMessage("这肯定不是阅读器的问题！\n绝对不是！（试试离线登录？）")
                builder.setPositiveButton("确定", null)
                builder.show()
            }
        })
    }

    private fun initTestData(){
        //book data
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