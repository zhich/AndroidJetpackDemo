package com.zch.demo.livedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.zch.demo.R
import kotlinx.android.synthetic.main.activity_live_data.*

/**
 * Created by zch on 2018/11/22.
 */
class LiveDataActivity : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        // 创建并注册观察者
        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        myViewModel.getName().observe(this, Observer {
            // LiveData 数据更新回调，it 代表被观察对象的数据，此处为 name
            Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
        })

        btnSetName.setOnClickListener {
            // 使用 setValue 更新 LiveData 数据
            myViewModel.getName().value = "张三"
        }
    }
}