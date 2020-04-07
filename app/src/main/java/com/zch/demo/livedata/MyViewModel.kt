package com.zch.demo.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by zch on 2018/11/22.
 */
class MyViewModel : ViewModel() {

    // 创建一个 String 类型的 LiveData
    private lateinit var name: MutableLiveData<String>

    fun getName(): MutableLiveData<String> {
        if (!::name.isInitialized) {
            name = MutableLiveData()
        }
        return name
    }
}