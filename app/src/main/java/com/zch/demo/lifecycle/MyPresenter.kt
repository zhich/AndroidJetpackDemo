package com.zch.demo.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.util.Log

/**
 * Created by zch on 2018/11/03.
 */
class MyPresenter : IPresenter {

    override fun onCreate(owner: LifecycleOwner) {
        Log.e(javaClass.simpleName, "onCreate")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.e(javaClass.simpleName, "onDestroy")
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        Log.e(javaClass.simpleName, "onLifecycleChanged")
    }
}