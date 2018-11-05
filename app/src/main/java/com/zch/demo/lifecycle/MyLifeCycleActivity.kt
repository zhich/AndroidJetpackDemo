package com.zch.demo.lifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.zch.demo.R

/**
 * Created by zch on 2018/11/05.
 */
class MyLifeCycleActivity : AppCompatActivity() {

    private lateinit var myPresenter: MyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_lifecycle)

        Log.e(javaClass.simpleName, "onCreate")

        myPresenter = MyPresenter()
        lifecycle.addObserver(myPresenter) // 添加 LifecycleObserver
    }

    override fun onDestroy() {
        Log.e(javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }
}