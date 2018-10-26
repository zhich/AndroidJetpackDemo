package com.zch.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zch.demo.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnViewModel.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewModelActivity::class.java))
        }
    }
}