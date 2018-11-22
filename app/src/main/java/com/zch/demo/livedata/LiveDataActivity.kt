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

        myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        myViewModel.getName().observe(this, Observer {
            Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
        })

        btnSetName.setOnClickListener {
            myViewModel.getName().value = "张三"
        }
    }
}