package com.example.bookreader

import android.content.DialogInterface
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bifan.txtreaderlib.bean.TxtChar
import com.bifan.txtreaderlib.bean.TxtMsg
import com.bifan.txtreaderlib.interfaces.ICenterAreaClickListener
import com.bifan.txtreaderlib.interfaces.ILoadListener
import com.bifan.txtreaderlib.interfaces.ITextSelectListener
import com.bifan.txtreaderlib.main.TxtConfig
import com.bifan.txtreaderlib.main.TxtReaderView
import kotlin.properties.Delegates


class ReaderActivity : AppCompatActivity() {
    private val bookString: String?
        get() {
            return intent.getStringExtra("bookString")
        }
    private val bookId: Long?
        get() {
            return intent.getStringExtra("bookId")?.toLong()
        }
    private val mBottomMenu: ConstraintLayout
        get() {
            return findViewById(R.id.readerMenuView)
        }
    private val hwTxtPlayView: TxtReaderView
        get() {
            val hwTxtPlayView = findViewById<TxtReaderView>(R.id.reader_readerView)
            return hwTxtPlayView
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reader)
        initReaderView()
        initMenu()
    }


    private fun initReaderView(){
        TxtConfig.saveIsOnVerticalPageMode(this,false)
        if(bookId == -1L){
            Log.e("ReaderActivity:", "书籍intent加载出错！！")
            finish()
        }
        hwTxtPlayView
            .loadText(bookString, object : ILoadListener {
                override fun onSuccess(){}
                override fun onMessage(message: String) {/*加载过程信息回调*/}
                override fun onFail(txtMsg: TxtMsg) {
                    //加载失败回调
                    runOnUiThread{
                        val builder = AlertDialog.Builder(MyApplication.context)
                            .setTitle("文件打开失败~")
                            .setMessage("再开一次试试看？")
                            .setPositiveButton("好的", object: DialogInterface.OnClickListener{
                                override fun onClick(p0: DialogInterface?, p1: Int) {
                                    initReaderView()
                                }
                            })
                        builder.show()
                    }
                }
            })
        hwTxtPlayView
            .setOnCenterAreaClickListener(object: ICenterAreaClickListener {
                override fun onCenterClick(widthPercentInView: Float): Boolean {
                    if(mBottomMenu.visibility == View.INVISIBLE){
                        mBottomMenu.visibility = View.VISIBLE
                        showMenu()
                    }else{
                        mBottomMenu.visibility = View.INVISIBLE
                        hideMenu()
                    }
                    return true
                }

                override fun onOutSideCenterClick(widthPercentInView: Float): Boolean {
                    //返回值为真则消耗一次点击事件，不翻页 -> 菜单可见时不翻页
                    return mBottomMenu.getVisibility() == View.VISIBLE
                }

            })
        TxtConfig.saveIsShowSpecialChar(this, false)
    }


    private fun hideMenu() {
        val mAnimation = AnimationUtils.loadAnimation(this, R.anim.fade)
        mBottomMenu.animation = mAnimation
        mAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                mBottomMenu.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }


        })
        mAnimation.startNow()
    }

    private fun showMenu() {
        val mAnimation = AnimationUtils.loadAnimation(this, R.anim.merge)
        mBottomMenu.animation = mAnimation
        mAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                mBottomMenu.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(p0: Animation?) {

            }

            override fun onAnimationRepeat(p0: Animation?) {
            }


        })
        mAnimation.startNow()
    }

    private fun initMenu(){
        val boldButton = findViewById<TextView>(R.id.readerBoldButton)
        val plusButton = findViewById<TextView>(R.id.textSizePlusButton)
        val minusButton = findViewById<TextView>(R.id.textSizeMinusButton)
        val whiteBgButton = findViewById<ImageView>(R.id.readerColorWhite)
        val blackBgButton = findViewById<ImageView>(R.id.readerColorNightBlack)
        val brownButton = findViewById<ImageView>(R.id.readerColorBrown)
        boldButton.setOnClickListener {
            if(boldButton.typeface == Typeface.defaultFromStyle(Typeface.BOLD)){
                boldButton.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                hwTxtPlayView.setTextBold(false)

            }else{
                boldButton.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                hwTxtPlayView.setTextBold(true)
            }
        }
        plusButton.setOnClickListener {
            hwTxtPlayView.textSize = hwTxtPlayView.textSize + 4
        }
        minusButton.setOnClickListener {
            hwTxtPlayView.textSize = hwTxtPlayView.textSize - 4
        }
        whiteBgButton.setOnClickListener{
            hwTxtPlayView.setStyle(resources.getColor(R.color.white), resources.getColor(R.color.black))
        }
        blackBgButton.setOnClickListener{
            hwTxtPlayView.setStyle(resources.getColor(R.color.black), resources.getColor(R.color.night_text))
        }
        brownButton.setOnClickListener{
            hwTxtPlayView.setStyle(resources.getColor(R.color.background_brown), resources.getColor(R.color.black))
        }
    }


}