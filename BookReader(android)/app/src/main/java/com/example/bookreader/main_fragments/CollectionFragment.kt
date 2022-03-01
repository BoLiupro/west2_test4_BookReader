package com.example.bookreader.main_fragments

import android.content.Intent
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
import com.example.bookreader.tools.CollectBook
import com.example.bookreader.tools.JsonToBookData
import com.example.bookreader.web_requests.RetrofitServiceCreator
import com.example.bookreader.web_requests.WebService
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CollectionFragment(private val userData: UserData) : BaseFragment() {
    private var collectionArray: ArrayList<BookData> = ArrayList()
    private var testCollectionArray: ArrayList<BookData> = ArrayList()
    private val jsonArray: ArrayList<JsonObject> = ArrayList()
    private lateinit var recyclerViewAdapter: BookRecyclerViewAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var mainActivity: MainActivity
    private val collectionConstraintLayout: View?
        get() {
            return view?.findViewById(R.id.collectionConstraint)
        }
    private val offlineNotice: ImageView?
        get() {
            val view = view?.findViewById<ImageView>(R.id.collectionOfflineNotice)
            return view
        }
    private val recyclerView: RecyclerView?
        get() {
            val view = view?.findViewById<RecyclerView>(R.id.collectionRecyclerView)
            return view
        }


    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?    ): View? {
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        recyclerViewAdapter = BookRecyclerViewAdapter(collectionArray, userData, mainActivity, 1, this)
        if(userData.id != 0L){
            getCollectionArray(userData)
            val recyclerView = activity?.findViewById<RecyclerView>(R.id.collectionRecyclerView)
            swipeRefresh = view.findViewById(R.id.collectionSwipeRefreshLayout)
            //初始化recyclerView
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
        getCollectionArray(userData)
    }

    private fun initTestData(){
        //book data
        val json = JsonObject()
        json.addProperty("id", "820")
        json.addProperty("bookName","COLLECTION TEST BOOK!")
        json.addProperty("intro", "INTRO OF TEST BOOK!")
        json.addProperty("rate", 5)
        json.addProperty("author", "MYSELF")
        json.addProperty("coverUrl", "https://pic.imgdb.cn/item/62021fc22ab3f51d91f18cd3.jpg")
        json.addProperty("tag", "轻小说")
        json.addProperty("isCollected", "false")
        jsonArray.add(json)
        Log.i("collectionFragment:", "json: $jsonArray")
        testCollectionArray = JsonToBookData.shortJsonToBookDataList(jsonArray)
        Log.i("test data init", testCollectionArray.toString())
    }

    private fun getCollectionArray(userData: UserData){
        collectionArray.clear()
        val webService = RetrofitServiceCreator.create(WebService::class.java)
        val call: Call<List<BookData>> = webService.getCollectedBook(userData.id.toInt())
        call.enqueue(object : Callback<List<BookData>> {
            override fun onResponse(
                call: Call<List<BookData>>,
                response: Response<List<BookData>>
            ) {
                val responseBody = response.body()
                if (responseBody != null){
                    collectionArray.addAll(response.body() as ArrayList)
                    for (bookData in collectionArray) {
                        Log.i("collectionFragment:", "received book data: $bookData")
                    }
                }else{
                    Log.w("collectionFragment:", "Empty response body! Check net work???")
                }
                recyclerViewAdapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<BookData>>, t: Throwable) {
                Log.i("collectionFragment:", "onFailure")
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

    fun makeSnackbar(bookDataData: BookData){
        mainActivity.moveFAB()
        view?.let {
            Log.i("snackBar", collectionConstraintLayout.toString())
            collectionConstraintLayout?.let { it1 ->
                Snackbar.make(it1, R.string.已取消收藏, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.撤销) {
                        CollectBook.collectBook(userData, bookDataData.id, this)
                    }
                    .show()
            }
        }
    }
}