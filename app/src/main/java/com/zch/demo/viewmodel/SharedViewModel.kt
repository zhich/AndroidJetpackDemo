package com.zch.demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by zch on 2018/10/26.
 */
class SharedViewModel : ViewModel() {

    val selected = MutableLiveData<User>()

    fun select(user: User) {
        selected.value = user
    }
}