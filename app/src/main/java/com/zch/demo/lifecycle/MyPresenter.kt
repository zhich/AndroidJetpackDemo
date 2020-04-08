package com.zch.demo.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.zch.demo.utils.LogUtil

/**
 * Created by zch on 2018/11/03.
 */
class MyPresenter : IPresenter {

    override fun onCreate(owner: LifecycleOwner) {
        LogUtil.e(javaClass.simpleName, "onCreate")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        LogUtil.e(javaClass.simpleName, "onDestroy")
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        LogUtil.e(javaClass.simpleName, "onLifecycleChanged")
    }
}