package com.zch.demo

import android.app.Application

/**
 * Created by zch on 2018/5/23.
 */
open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: BaseApp? = null
            protected set
    }
}