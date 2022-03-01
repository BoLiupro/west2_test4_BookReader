package com.example.bookreader

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.bookreader.ActivityCollector.finishAll
import com.example.bookreader.data_class.UserData
import com.example.bookreader.main_fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
class MainActivity : BaseActivity() {
    private val btmNavBar: BottomNavigationView
        get() {
            return findViewById(R.id.bottomNavigationBar)
        }
    private var previousFragmentId = -1
    private lateinit var userData: UserData
    private val searchFab: FloatingActionButton
        get() {
            val searchFab = findViewById<FloatingActionButton>(R.id.searchFab)
            return searchFab
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUserData(intent)
        init()
    }

    private fun init(){
        //functions that handle changing of fragments:
        fun setHomeFragment(){
            searchFab.setImageResource(R.drawable.ic_search)
            searchFab.setOnClickListener {
                val intent = Intent(this, SearchActivity::class.java)
                Log.i("mainActivity:", "$userData")
                intent.putExtra("userData", userData)
                intent.putExtra("searchMode", 0)
                this.startActivity(intent)
            }
            replaceFragment(HomeFragment(userData, this), 0)
        }
        fun setCollectionFragment(){
            searchFab.setImageResource(R.drawable.ic_search_like)
            searchFab.setOnClickListener {
                val intent = Intent(this, SearchActivity::class.java)
                Log.i("mainActivity:", "$userData")
                intent.putExtra("searchMode", 1)
                intent.putExtra("userData", userData)
                this.startActivity(intent)
            }
            replaceFragment(CollectionFragment(userData), 1)
        }
        fun setDownloadFragment(){
            replaceFragment(DownLoadFragment(userData), 2)
            searchFab.hide()
        }
        fun setHistoryFragment(){
            replaceFragment(HistoryFragment(userData, this), 3)
            searchFab.hide()
        }
        fun setSettingsFragment(){
            replaceFragment(SettingsFragment(this, userData), 4)
            searchFab.hide()
        }

        //default fragment, offlineLogin considered:
        if (intent.getBooleanExtra("isOffline", false)){
            setDownloadFragment()
            btmNavBar.selectedItemId = R.id.navDownload
            //nav_bar onClickListeners
            btmNavBar.setOnItemSelectedListener{
                when(it.itemId){
                    R.id.navHome -> {
                        setHomeFragment()
                    }
                    R.id.navCollection -> {
                        setCollectionFragment()
                    }
                    R.id.navDownload -> {
                        setDownloadFragment()
                    }
                    R.id.navHistory -> {
                        setHistoryFragment()
                    }
                    R.id.navSettings -> {
                        setSettingsFragment()
                    }
                }
                it.isChecked = true
                false
            }
        }else {
            //nav_bar onClickListeners
            btmNavBar.setOnItemSelectedListener{
                when(it.itemId){
                    R.id.navHome -> {
                        searchFab.show()
                        setHomeFragment()
                    }
                    R.id.navCollection -> {
                        searchFab.show()
                        setCollectionFragment()
                    }
                    R.id.navDownload -> {
                        setDownloadFragment()
                    }
                    R.id.navHistory -> {
                        setHistoryFragment()
                    }
                    R.id.navSettings -> {
                        setSettingsFragment()
                    }
                }
                it.isChecked = true
                false
            }
            setHomeFragment()
        }

        //hide title bar:
        supportActionBar?.hide()
    }

    //navigation bar animation/actions：

    private fun replaceFragment(fragment: BaseFragment, itemId: Int){
        val fragmentManager = supportFragmentManager
        if(itemId == -1){
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrameLayout, fragment)
            //这里不安全？
            transaction.commit()
        }
        if(itemId > previousFragmentId){
            val transaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out)
            transaction.replace(R.id.contentFrameLayout, fragment)
            transaction.commit()
            previousFragmentId = itemId
        }
        if(itemId < previousFragmentId){
            val transaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out)
            transaction.replace(R.id.contentFrameLayout, fragment)
            transaction.commit()
            previousFragmentId = itemId
        }
    }

    //双击退出部分：
    private var exitTime: Long = 100
    override fun onBackPressed() {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            exitApp()
        }else {
            return super.onKeyDown(keyCode, event)
        }
        return true
    }

    private fun exitApp(){
        if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
        {
            Toast.makeText(this, "再点一次退出", Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finishAll()
        }
    }

    //获取用户信息
    private fun getUserData(intent: Intent){
        userData = intent.getSerializableExtra("userData") as UserData
    }

    //在fragment中弹出snackBar时调用
    fun moveFAB(){
        val mAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up_down)
        searchFab.animation = mAnimation
        mAnimation.startNow()
    }

    fun hideFab(){
        searchFab.hide()
    }

}