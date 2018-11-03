package com.zch.demo.lifecycle

import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.LifecycleOwner

/**
 * Created by zch on 2018/11/03.
 */
interface IPresenter : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner)

    override fun onDestroy(owner: LifecycleOwner)
}