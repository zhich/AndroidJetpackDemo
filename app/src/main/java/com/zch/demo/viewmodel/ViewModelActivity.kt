package com.zch.demo.viewmodel

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.zch.demo.R

/**
 * Created by zch on 2018/10/26.
 */
class ViewModelActivity : AppCompatActivity() {

    private val TAG = "ViewModelActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        testViewModel()

        testAndroidViewModel()
    }

    private fun testViewModel() {
        // 就算配置更改（如屏幕旋转）了，获取到的 userViewModel 对象还会是上一次的 UserViewModel 对象
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        // 这里的 this 需要用实现了 LifecycleOwner 的类的 this . 如 AppCompatActivity、FragmentActivity
        userViewModel.getUsers().observe(this, Observer {
            Log.e(TAG, it.toString())
            // 打印结果：[User(id=1, name=AA), User(id=2, name=BB)]
        })
    }

    private fun testAndroidViewModel() {
        val myAndroidViewModel = ViewModelProviders.of(this).get(MyAndroidViewModel::class.java)
        Log.e(TAG, myAndroidViewModel.getStatus(2))
        // 打印结果：早退
    }
}