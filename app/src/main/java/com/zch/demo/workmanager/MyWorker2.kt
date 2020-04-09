package com.zch.demo.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.zch.demo.utils.LogUtil
import kotlinx.coroutines.delay

/**
 * Created by zch on 2020-04-09.
 */
class MyWorker2(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        LogUtil.d("MyWorker2")
        return Result.success()
    }
}