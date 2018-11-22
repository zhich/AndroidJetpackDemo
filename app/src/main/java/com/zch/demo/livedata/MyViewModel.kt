package com.zch.demo.livedata

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by zch on 2018/11/22.
 */
class MyViewModel : ViewModel() {

    private lateinit var name: MutableLiveData<String>

    fun getName(): MutableLiveData<String> {
        if (!::name.isInitialized) {
            name = MutableLiveData()
        }
        return name
    }
}