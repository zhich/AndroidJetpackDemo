package com.zch.demo.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zch.demo.R
import com.zch.demo.utils.LogUtil

/**
 * Created by zch on 2018/11/05.
 */
class MyLifecycleActivity : AppCompatActivity() {

    private lateinit var myPresenter: MyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_lifecycle)

        LogUtil.d(javaClass.simpleName, "onCreate")

        myPresenter = MyPresenter()
        lifecycle.addObserver(myPresenter) // 添加 LifecycleObserver
    }

    override fun onDestroy() {
        LogUtil.d(javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }
}