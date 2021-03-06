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
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment(private val userData: UserData, private val mainActivity: MainActivity): BaseFragment() {
    private var testBookDataArray: ArrayList<BookData> = ArrayList()
    private var bookDataArray: ArrayList<BookData> = ArrayList()
    private val jsonArray: ArrayList<JsonObject> = ArrayList()
    private lateinit var recyclerViewAdapter: BookRecyclerViewAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val offlineNotice: ImageView?
        get() {
            val view = view?.findViewById<ImageView>(R.id.homeOfflineNotice)
            return view
        }
    private val recyclerView: RecyclerView?
        get() {
            val view = view?.findViewById<RecyclerView>(R.id.homeRecyclerView)
            return view
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewAdapter = BookRecyclerViewAdapter(bookDataArray, userData, mainActivity, 0, this)
        swipeRefresh = view.findViewById(R.id.homeSwipeRefreshLayout)

        if(userData.id != 0L){
        //??????????????????
            getBookArray()
        //?????????recyclerView
            val recyclerView = activity?.findViewById<RecyclerView>(R.id.homeRecyclerView)
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
        val call: Call<List<BookData>> = webService.getBookRecommend(userData.id.toInt())
        call.enqueue(object : Callback<List<BookData>> {
            override fun onResponse(
                call: Call<List<BookData>>,
                response: Response<List<BookData>>) {
                val responseBody = response.body()
                if (responseBody != null){
                    bookDataArray.addAll(responseBody as ArrayList)
                    for (bookData in bookDataArray) {
                        Log.i("homeFragment:", "received book data: $bookData")
                    }
                }else{
                    Log.w("homeFragment:", "Empty response body! Check net work???")
                }
                recyclerViewAdapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<List<BookData>>, t: Throwable) {
                Log.i("HomeFragment:", "onFailure")
                t.printStackTrace()
                swipeRefresh.isRefreshing = false
                val builder = AlertDialog.Builder(mainActivity)
                builder.setTitle("????????????????????????\n(?????????????????????)")
                builder.setMessage("????????????????????????????????????\n??????????????????????????????????????????")
                builder.setPositiveButton("??????", null)
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
        json.addProperty("tag", "?????????")
        json.addProperty("isCollected", "false")
        val json2 = JsonObject()
        json2.addProperty("id", "14514")
        json2.addProperty("bookName","TEST BOOK!")
        json2.addProperty("intro", "INTRO OF TEST BOOK!")
        json2.addProperty("rate", 5)
        json2.addProperty("author", "MYSELF")
        json2.addProperty("coverUrl", "https://pic.imgdb.cn/item/62021fc22ab3f51d91f18cd3.jpg")
        json2.addProperty("tag", "?????????")
        json2.addProperty("isCollected", "true")
        jsonArray.add(json)
        jsonArray.add(json2)
        testBookDataArray.clear()
        testBookDataArray.addAll(JsonToBookData.shortJsonToBookDataList(jsonArray))
        jsonArray.clear()
        Log.i("test data init", testBookDataArray.toString())
    }

}