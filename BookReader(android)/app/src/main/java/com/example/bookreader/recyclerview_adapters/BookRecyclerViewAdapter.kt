package com.example.bookreader.recyclerview_adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.bookreader.DetailActivity
import com.example.bookreader.MyApplication
import com.example.bookreader.R
import com.example.bookreader.data_class.BookData
import com.example.bookreader.data_class.UserData
import com.example.bookreader.main_fragments.BaseFragment
import com.example.bookreader.main_fragments.CollectionFragment
import com.example.bookreader.main_fragments.DownLoadFragment
import com.example.bookreader.main_fragments.HomeFragment
import com.example.bookreader.tools.CollectBook
import com.example.bookreader.tools.LocalBookUtil
import com.google.android.material.card.MaterialCardView
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.Serializable
import java.text.SimpleDateFormat
import kotlin.Int
import kotlin.collections.ArrayList

class BookRecyclerViewAdapter(private var bookDataList: ArrayList<BookData>, private val userData: UserData,
                              private val mainActivity: Activity?, private val mode: Int,
                              private val fragment: Fragment?): RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val bookImageView: ImageView = view.findViewById(R.id.bookCoverImageView)
        val bookNameTextView: TextView = view.findViewById(R.id.bookName)
        val bookAuthorTextView: TextView = view.findViewById(R.id.bookAuthor)
        val menuButton: ImageButton = view.findViewById(R.id.homeCardMenuButton)
        val collectedMark: ImageView = view.findViewById(R.id.collectedImageView)
        val lastReadTimeImage: ImageView = view.findViewById(R.id.lastReadTimeImageView)
        val lastReadTimeText: TextView = view.findViewById(R.id.lastReadTimeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_recycler_view, parent, false)
        //onClick
        val viewHolder = ViewHolder(view)
        val card = view.findViewById<MaterialCardView>(R.id.bookCard)
        card.setOnClickListener{
            val position = viewHolder.adapterPosition
            onBookClick(bookDataList[position], userData)
        }
        Log.i("recyclerview adapter:", "mode: $mode data list: $bookDataList")
        when(mode){
            0 -> viewHolder.menuButton.setOnClickListener{
                val position = viewHolder.adapterPosition
                onHomeMenuClick(bookDataList[position], viewHolder)
            }
            1 -> viewHolder.menuButton.setOnClickListener{
                val position = viewHolder.adapterPosition
                onCollectionMenuClick(bookDataList[position], viewHolder)
            }
            2 -> viewHolder.menuButton.setOnClickListener{
                val position = viewHolder.adapterPosition
                onDownloadMenuClick(bookDataList[position], viewHolder)
            }
            3 -> viewHolder.menuButton.setOnClickListener{
                val position = viewHolder.adapterPosition
                onSearchMenuClick(bookDataList[position], viewHolder)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mode == 3){
            val timeStamp = bookDataList[position].lastReadTime.toLong()
            val localTimeStamp = timeStamp + 8*60*60*1000 //东八区时间
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val localTime = simpleDateFormat.format(localTimeStamp)
            val time = simpleDateFormat.format(timeStamp)
            Log.i("recyclerViewAdapter:", "GMT: $timeStamp, GMT+8: $localTimeStamp, " +
                    "local time: $localTime, time: $time")
            holder.lastReadTimeText.text = localTime
            holder.lastReadTimeImage.visibility = View.VISIBLE
            holder.lastReadTimeText.visibility = View.VISIBLE
        }
        val book = bookDataList[position]
        val url = book.coverUrl
        Glide.with(MyApplication.context).load(url)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .apply(RequestOptions.bitmapTransform(
                RoundedCornersTransformation(12, 0,
                    RoundedCornersTransformation.CornerType.ALL)
            ))
            .into(holder.bookImageView)
        holder.bookNameTextView.text = book.name
        holder.bookAuthorTextView.text = book.author
        if (bookDataList[position].isCollected){
            holder.collectedMark.visibility = View.VISIBLE
        }else{
            holder.collectedMark.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return bookDataList.size
    }

    private fun onBookClick(bookData: BookData, userData: UserData){
        val intent = Intent(MyApplication.context, DetailActivity::class.java)
        intent.putExtra("userData", userData as Serializable)
        intent.putExtra("bookData", bookData as Serializable)
        Log.i("recyclerViewAdapter:", "${bookData}")
        mainActivity?.startActivityForResult(intent, 1)
    }



    private fun onHomeMenuClick(bookData: BookData, viewHolder: ViewHolder){
        val popUpMenu = PopupMenu(MyApplication.context, viewHolder.menuButton)
        Log.i("recyclerViewAdapter:", "is the bookData collected? ${bookData.isCollected}")
        if (bookData.isCollected){
            Log.i("recyclerViewAdapter:", "showing collected version menu")
            popUpMenu.menuInflater.inflate(R.menu.pop_up_menu_home_collected, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.homePopUpCollect -> CollectBook.unCollectBook(userData, bookData.id, fragment as HomeFragment)
                    R.id.homePopUpDownload -> LocalBookUtil.downloadBook(bookData, mainActivity!!)
                }
                true
            }
            popUpMenu.show()
        }else{
            Log.i("recyclerViewAdapter:", "showing UN-collected version menu")
            popUpMenu.menuInflater.inflate(R.menu.pop_up_menu_home, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.homePopUpCollect -> CollectBook.collectBook(userData, bookData.id, fragment as HomeFragment)
                    R.id.homePopUpDownload -> LocalBookUtil.downloadBook(bookData, mainActivity!!)
                }
                true
            }
            popUpMenu.show()
        }
    }

    private fun onCollectionMenuClick(bookData: BookData, viewHolder: ViewHolder){
        val popUpMenu = PopupMenu(MyApplication.context, viewHolder.menuButton)
        popUpMenu.menuInflater.inflate(R.menu.pop_up_menu_collection, popUpMenu.menu)
        popUpMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.collectionPopUpUnCollect -> {
                    val collectionFragment = fragment as CollectionFragment
                    CollectBook.unCollectBook(userData, bookData.id, collectionFragment)
                    collectionFragment.makeSnackbar(bookData)
                }
                R.id.collectionPopUpDownload -> LocalBookUtil.downloadBook(bookData, mainActivity!!)
            }
            false
        }
        popUpMenu.show()
    }

    private fun onDownloadMenuClick(bookData: BookData, viewHolder: ViewHolder){
        val popUpMenu = PopupMenu(MyApplication.context, viewHolder.menuButton)
        popUpMenu.menuInflater.inflate(R.menu.pop_up_menu_download, popUpMenu.menu)
        popUpMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.downloadPopUpDelete -> {
                    LocalBookUtil.deleteBook(bookData, fragment as DownLoadFragment)
                }
                R.id.collectionPopUpDownload -> LocalBookUtil.downloadBook(bookData, mainActivity!!)
            }
            false
        }
        popUpMenu.show()
    }

    private fun onSearchMenuClick(bookData: BookData, viewHolder: ViewHolder){
        val popUpMenu = PopupMenu(MyApplication.context, viewHolder.menuButton)
        Log.i("recyclerViewAdapter:", "is the bookData collected: ${bookData.isCollected}")
        if (bookData.isCollected){
            Log.i("recyclerViewAdapter:", "showing collected version menu")
            popUpMenu.menuInflater.inflate(R.menu.pop_up_menu_home_collected, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.homePopUpCollect -> CollectBook.unCollectBook(userData, bookData.id, fragment as BaseFragment)
                    R.id.homePopUpDownload -> LocalBookUtil.downloadBook(bookData, mainActivity!!)
                }
                true
            }
            popUpMenu.show()
        }else{
            Log.i("recyclerViewAdapter:", "showing UN-collected version menu")
            popUpMenu.menuInflater.inflate(R.menu.pop_up_menu_home, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.homePopUpCollect -> CollectBook.collectBook(userData, bookData.id, fragment as BaseFragment)
                    R.id.homePopUpDownload -> LocalBookUtil.downloadBook(bookData, mainActivity!!)
                }
                true
            }
            popUpMenu.show()
        }
    }

}
