package com.zch.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by zch on 2018/10/26.
 */
class UserViewModel : ViewModel() {

    private lateinit var users: MutableLiveData<List<User>>

    fun getUsers(): LiveData<List<User>> {
        if (!::users.isInitialized) {
            users = MutableLiveData()
            loadUsers()
        }
        return users
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users .
        Thread(Runnable {
            Thread.sleep(3000)
            // 由于在子线程发送值需要用 postValue , 否则用 setValue 就可以了。
            users.postValue(listOf(User("1", "AA"), User("2", "BB")))
        }).start()
    }
}