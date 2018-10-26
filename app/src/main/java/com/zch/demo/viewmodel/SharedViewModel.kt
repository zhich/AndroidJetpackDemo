package com.zch.demo.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by zch on 2018/10/26.
 */
class SharedViewModel : ViewModel() {

    val selected = MutableLiveData<User>()

    fun select(user: User) {
        selected.value = user
    }
}