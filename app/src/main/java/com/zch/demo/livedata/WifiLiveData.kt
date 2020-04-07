package com.zch.demo.livedata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import androidx.lifecycle.LiveData
import java.lang.ref.WeakReference

/**
 * Created by zch on 2018/11/24.
 */

class WifiLiveData private constructor(context: Context) : LiveData<Int>() {

    private var mContext: WeakReference<Context> = WeakReference(context)

    companion object {

        private var instance: WifiLiveData? = null

        fun getInstance(context: Context): WifiLiveData {
            if (instance == null) {
                instance = WifiLiveData(context)
            }
            return instance!!
        }
    }

    override fun onActive() {
        super.onActive()
        registerReceiver()
    }

    override fun onInactive() {
        super.onInactive()
        unregisterReceiver()
    }

    /**
     * 注册广播，监听 Wifi 信号强度
     */
    private fun registerReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION)
        mContext.get()!!.registerReceiver(mReceiver, intentFilter)
    }

    /**
     * 反注册广播
     */
    private fun unregisterReceiver() {
        mContext.get()!!.unregisterReceiver(mReceiver)
    }

    private val mReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            when (intent.action) {
                WifiManager.RSSI_CHANGED_ACTION -> getWifiLevel()
            }
        }
    }

    private fun getWifiLevel() {
        val wifiManager = mContext.get()!!.applicationContext.getSystemService(android.content.Context.WIFI_SERVICE) as WifiManager
        val wifiInfo = wifiManager.connectionInfo
        val level = wifiInfo.rssi

        instance!!.value = level // 发送 Wifi 的信号强度给观察者
    }
}