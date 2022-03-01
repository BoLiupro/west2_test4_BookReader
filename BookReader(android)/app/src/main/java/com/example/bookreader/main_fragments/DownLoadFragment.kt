package com.example.bookreader.main_fragments

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookreader.MainActivity
import com.example.bookreader.MyApplication
import com.example.bookreader.R
import com.example.bookreader.data_base.BookDataBase
import com.example.bookreader.data_class.BookData
import com.example.bookreader.data_class.UserData
import com.example.bookreader.recyclerview_adapters.BookRecyclerViewAdapter
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class DownLoadFragment( private val userData: UserData) : BaseFragment() {
    private var downloadArray: ArrayList<BookData> = ArrayList()
    private var deleteArray: ArrayList<BookData> = ArrayList()
    private val recyclerViewAdapter: BookRecyclerViewAdapter
        get() {
            return BookRecyclerViewAdapter(downloadArray, userData, activity as MainActivity, 2, this)
        }
    private val lock = ReentrantLock()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_download, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    override fun onDestroy() {
        thread{
            for (bookData in deleteArray) {
                val bookDataDao = BookDataBase.getDatabase(MyApplication.context).bookDataDao()
                bookDataDao.deleteBookData(bookData)
            }
        }
        super.onDestroy()
    }

    override fun refreshData() {
        thread {
            try {
                lock.lock()
                val bookDataDao = BookDataBase.getDatabase(MyApplication.context).bookDataDao()
                downloadArray = bookDataDao.loadAllBookData() as ArrayList<BookData>
                recyclerViewAdapter.notifyDataSetChanged()
                initRecyclerView()
                Log.i("download fragment:", "downloadArray after refresh: $downloadArray")
            }finally {
                lock.unlock()
            }
        }
    }

    private fun initRecyclerView(){// and data
        thread {
           try{
               lock.lock()
               val bookDataDao = BookDataBase.getDatabase(MyApplication.context).bookDataDao()
               downloadArray = bookDataDao.loadAllBookData() as ArrayList<BookData>
               activity?.runOnUiThread{
                   val recyclerView = activity?.findViewById<RecyclerView>(R.id.downloadRecyclerView)
                   recyclerView?.layoutManager = LinearLayoutManager(activity)
                   recyclerView?.adapter = recyclerViewAdapter
               }
               Log.i("download fragment:", "download array in thread: $downloadArray")
           }finally {
               lock.unlock()
           }
        }
    }
}