package com.zch.demo.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.zch.demo.utils.LogUtil
import kotlinx.coroutines.delay

/**
 * Created by zch on 2020-04-08.
 */
class SleepWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        delay(2000)
        LogUtil.d("SleepWorker")
        return Result.success()
    }
}