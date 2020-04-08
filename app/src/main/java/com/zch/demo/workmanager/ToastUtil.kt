package com.zch.demo.workmanager

import android.view.Gravity
import android.widget.Toast
import com.zch.demo.BaseApp

/**
 * Created by zch on 2019/02/20.
 */
object ToastUtil {

    fun showShortText(text: String?) {
        val toast = Toast.makeText(BaseApp.instance, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showLongText(text: String?) {
        val toast = Toast.makeText(BaseApp.instance, text, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}