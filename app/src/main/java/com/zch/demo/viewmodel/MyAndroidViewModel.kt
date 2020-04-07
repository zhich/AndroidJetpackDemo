package com.zch.demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.zch.demo.R

/**
 * Created by zch on 2018/10/26.
 */
class MyAndroidViewModel(application: Application) : AndroidViewModel(application) {

    private val app
        get() = getApplication<Application>()

    fun getStatus(code: Int): String {
        return when (code) {
            1 -> app.resources.getString(R.string.be_late) // 迟到
            2 -> app.resources.getString(R.string.leave_early) // 早退
            else -> app.resources.getString(R.string.absenteeism) // 旷工
        }
    }
}