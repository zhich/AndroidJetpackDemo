package com.zch.demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zch.demo.lifecycle.MyLifecycleActivity
import com.zch.demo.livedata.LiveDataActivity
import com.zch.demo.viewmodel.ViewModelActivity
import com.zch.demo.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnViewModel?.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewModelActivity::class.java))
        }

        btnLifecycle?.setOnClickListener {
            startActivity(Intent(this@MainActivity, MyLifecycleActivity::class.java))
        }

        btnLiveData?.setOnClickListener {
            startActivity(Intent(this@MainActivity, LiveDataActivity::class.java))
        }

        btnWorkManager?.setOnClickListener {
            startActivity(Intent(this@MainActivity, WorkManagerActivity::class.java))
        }
    }
}